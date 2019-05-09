package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.constant.TrainConst;
import com.heyx.jsoup.dao.net.NetworkRepo;
import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.entity.net.*;
import com.heyx.jsoup.service.BaseService;
import com.heyx.jsoup.service.dot.HistoryService;
import com.heyx.jsoup.util.CountStringUtils;
import com.heyx.jsoup.util.FormatUtils;
import com.heyx.jsoup.util.MathUtils;
import kotlin.jvm.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class NetworkService extends BaseService<Network, String> {

    @Autowired
    NetworkRepo networkRepo;

    @Autowired
    NodeService nodeService;

    @Autowired
    LayerService layerService;

    @Autowired
    LineService lineService;

    @Autowired
    InfoService infoService;

    @Autowired
    HistoryService historyService;

    /**
     * 产生结果
     *
     * @return
     */
    public byte[] generate() {
        //TODO
        return new byte[8];
    }

    /**
     * 训练
     * 思路：
     * 1. 用结果的差值去拟合，
     * 保持其他的节点不变，直到调节到最小的位置，然后调节其他的，直到达到一定的条件
     * if(diff - lastDiff 变大直到 满足一定的条件){
     * w 向上一次相反的方向移动。
     * }else{
     * w 向上一次相同的方向移动。
     * }
     * <p>
     * 2.过程，计算，之后调节参数，再计算，直到 符合结果
     * <p>
     * 3.一半一半去调节，从大到小
     */
    public void train(Network network, String startCode) {
        int adjust = 1;
        HashMap<String, Integer> maxArgs = null;
        HashMap<String, Integer> minArgs = null;
        int lastRate = 0;
        int count = 0; //用于记录连续的几次值
        int num = 0;
        if (network == null){
            return;
        }
        List<Layer> layers = layerService.findAllByNetwork(network);
        List<Layer> layerList = layerService.sortByParentId(layers);
        int stepSize = layerList.get(0).getNodeNum() / NodeConst.MIN_NODE_NUM;
        String endCode = MathUtils.getCode(startCode, stepSize);
        String good = historyService.findGoodByCode(endCode);
        if (StringUtils.isBlank(good)){
            System.out.println("========------GOOD------=========------IS-------=========-----NONE------=========");
            return;
        }
        List<History> histories = historyService.findAllByCodeBetween(startCode, endCode);
        String input = historyService.convertToSampleMatrixWithStep(histories, stepSize);
        List<String> ids = getNodeAndLineIDs(layerList);
        while ((lastRate < NodeConst.MIN_NODE_NUM - NodeConst.ERROR_NODE_RATE) ||
                (num < TrainConst.MAX_TRAIN_NUM) ) {

            num ++;
            System.out.println("-正在计算第 " + startCode +" 期,第 "+ num +" 次的数据");

            //1. 生成 一组用来计算的调节参数  a-正向调节 b-反向调节
            HashMap<String, Integer> args = dynamicTuning(ids, maxArgs, minArgs, adjust);
            //2.计算结果
            String result = calc(network, layerList, input, args);
            int rate = historyService.compareResult(result, good);
            if (rate < lastRate) {
                count++;
                if (count == TrainConst.MAX_COUNT_NUM){
                    count = 0;
                    //多次没有效果进行转换调节方向
                    if (adjust == 1){
                        adjust = -1;
                    }else {
                        adjust = 1;
                    }
                }
                //反向调节参数
                minArgs = args;
            } else {
                //说明调节有效果，进行保存
                saveResult(layerList, args);
                adjust = 1;
                maxArgs = args;
            }
            lastRate = rate;

        }
        System.out.println( "============第 "+startCode+" 期数据，训练结束了！ =============");
    }

    private List<String> getNodeAndLineIDs(List<Layer> layerList) {
        List<String> list = new ArrayList<>();
        for (Layer layer : layerList) {
            List<Node> nodeList = nodeService.findAllByLayer(layer);
            for (Node node : nodeList) {
                List<Line> lines = lineService.findAllByOutput(node);
                for (Line line : lines) {
                    list.add(line.getId());
                }
                list.add(node.getId());
            }
        }
        return list;
    }

    /**
     * 动态调节
     *
     * @return 返回每个节点的调节结果
     * @param maxArgs 上一次优的结果
     * @param minArgs 上一次不好的结果
     * @param adjust 调节方向
     */
    public synchronized HashMap<String, Integer> dynamicTuning(
            List<String> list, HashMap<String, Integer> maxArgs, HashMap<String, Integer> minArgs, int adjust) {
        HashMap<String, Integer> args = new HashMap<>();
        for (String id : list) {
            int NODE_START = -NodeConst.MAX;
            int NODE_END = NodeConst.MAX;
            if (adjust == 1){
                if (maxArgs != null){
                    int value = maxArgs.get(id);
                    if( value >= 0){
                        NODE_START = value;
                    }else {
                        NODE_END = value;
                    }
                }
            }else if(adjust == -1) {
                if (maxArgs != null){
                    int value = maxArgs.get(id);
                    if( value >= 0){
                        NODE_END = value;
                    }else {
                        NODE_START = value;
                    }
                }
            }
            int NODE_BOUND = NODE_END - NODE_START + 1;
            args.put(id, new Random().nextInt(NODE_BOUND) + NODE_START);
            System.out.println("NODE_END " + NODE_END);
            System.out.println("NODE_START " + NODE_START);
            System.out.println("NODE_BOUND " + NODE_BOUND);
        }
        return args;
    }

    public void saveResult(List<Layer> layerList, HashMap<String, Integer> result) {
        for (Layer layer : layerList) {
            List<Node> nodeList = nodeService.findAllByLayer(layer);
            List<Node> changeNodeList = new ArrayList<>();
            int nodeNum = 0;
            for (Node node : nodeList) {
                List<Line> lines = lineService.findAllByOutput(node);
                List<Line> changeLineList = new ArrayList<>();
                int lineNum = 0;
                for (Line line : lines) {
                    Integer weight = result.get(line.getId());
                    if (null != weight) {
                        weight += line.getWeight();
                        line.setWeight(weight);
                        changeLineList.add(line);
                        lineNum++;
                    }

                    if (lineNum == 100) {
                        lineService.saveAll(changeLineList);
                        changeLineList.clear();
                        lineNum = 0;
                    }
                }
                lineService.saveAll(changeLineList);
                Integer bias = result.get(node.getId());
                if (null != bias) {
                    bias += node.getBias();
                    node.setBias(bias);
                    changeNodeList.add(node);
                    nodeNum++;
                }
                if (nodeNum == 100) {
                    nodeService.saveAll(changeNodeList);
                    changeNodeList.clear();
                    nodeNum = 0;
                }

            }
            nodeService.saveAll(changeNodeList);
        }
    }

    /**
     * 计算一次
     */
    public String calc(Network network, List<Layer> layerList, String input,HashMap<String, Integer> args) {
        if (null == network) {
            return "";
        }
        int inputNum = layerList.get(0).getNodeNum();
        Map<Integer, Double> lastLayerVlaue = new HashMap<>();
        for (Layer layer : layerList) {
            Map<Integer, Double> valueMap = new HashMap<>();
            List<Node> nodeList = nodeService.findAllByLayer(layer);
            if (LayerConst.INPUT_LAYER_ID.equals(layer.getParentId())) {
                for (int i = 0; i < inputNum; i++) {
                    double y = FormatUtils.getIndexValue(input, i) + nodeList.get(i).getBias();
                    double value = MathUtils.sigmoid(y);
                    valueMap.put(nodeList.get(i).getSize(), value);
                }
            } else {
                for (Node node : nodeList) {
                    List<Line> lines = lineService.findAllByOutput(node);
                    double value = 0.0;
                    Integer nodeValue = args.get(node.getId());
                    if (nodeValue != null){
                        node.setBias(node.getBias() + nodeValue);
                    }
                    for (Line line : lines) {
                        Integer lineValue = args.get(line.getId());
                        if (lineValue != null) {
                            line.setWeight(line.getWeight() + lineValue);
                        }
                        value += lastLayerVlaue.get(line.getInput().getSize()) *
                                line.getWeight() / NodeConst.LINE_FACTOR_NUM +
                                node.getBias();
                    }

                    valueMap.put(node.getSize(), MathUtils.sigmoid(value));
                }
            }
            lastLayerVlaue = valueMap;
        }
        int outputSize = lastLayerVlaue.size();
//        double threshold = (infoService.getNodeNum(network.getInfo()) / NodeConst.LINE_FACTOR_NUM) * NodeConst.LINE_OUTPUT_THRESHOLD;
        double threshold = NodeConst.LINE_OUTPUT_THRESHOLD;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < outputSize; i++) {
            System.out.println(" the value output of " + i + " is " + lastLayerVlaue.get(i));
            if (lastLayerVlaue.get(i) > threshold) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    /**
     * 简单检查两个网络是否相同
     */
    public boolean checkNetwork(Network m, Network n) {
        if ((null == m) || (null == n)) {
            return false;
        }
        return m.getInfo().equals(n.getInfo());
    }

    /**
     * 检查两个网络是否相同
     */
    public boolean checkNetworkLayer(Network m, Network n) {
        if ((null == m) || (null == n)) {
            return false;
        }
        if (!m.getInfo().equals(n.getInfo())) {
            return false;
        }

        List<Layer> m_src_layer = layerService.findAllByNetwork(m);
        List<Layer> n_src_layer = layerService.findAllByNetwork(n);
        List<Layer> m_layer = layerService.sortByParentId(m_src_layer);
        List<Layer> n_layer = layerService.sortByParentId(n_src_layer);

        int layerNum = m_layer.size() <= n_layer.size() ? m_layer.size() : n_layer.size();
        for (int i = 0; i < layerNum; i++) {
            int m_line_num = m_layer.get(i).getLineNum();
            int n_line_num = n_layer.get(i).getLineNum();
            if (m_line_num != n_line_num) {
                return false;
            }

            int m_node_num = m_layer.get(i).getNodeNum();
            int n_node_num = n_layer.get(i).getNodeNum();
            if (m_node_num != n_node_num) {
                return false;
            }
        }

        return true;
    }

    /**
     * 生成一个神经网络
     */
    public void generateNetWork(Info info) {
        if (!infoService.isValid(info)) {
            return;
        }
        info.setUsed(true);
        infoService.save(info);
        Network network = new Network(info);
        network = networkRepo.save(network);
        List<String> layerInfos = CountStringUtils.splitString(info.getLayerInfo(), "-");
        String parentId = LayerConst.INPUT_LAYER_ID;
        Layer lastLayer = null;
        for (String layerInfo : layerInfos) {
            int nodeNum = Integer.valueOf(layerInfo.split(":")[1].split("@")[0]);
            int lineNum = Integer.valueOf(layerInfo.split(":")[0]);
            int lineMode = Integer.valueOf(layerInfo.split("@")[1]);

            Layer layer = generateLayer(network, parentId, nodeNum, lineNum);
            if (lastLayer != null) {
                if (!generateLine(lastLayer, layer, lineNum, lineMode)) {
                    System.out.println("the line of " + lastLayer.getId() + " and " + layer.getId() + " generate false");
                }
            }
            lastLayer = layer;
            parentId = layer.getId();
        }
    }

    /**
     * 生成一层的节点
     * 输入层 nodeNum：NodeConst.MIN_NODE_NUM * historyNum;
     * 输出层 nodeNum：NodeConst.MIN_NODE_NUM
     */

    public Layer generateLayer(Network network, String parentId, int nodeNum, int lineNum) {
        if (network == null) {
            return null;
        }
        if (nodeNum < NodeConst.MIN_NODE_NUM || nodeNum > NodeConst.MAX_NODE_NUM) {
            return null;
        }
        if (StringUtils.isBlank(parentId)) {
            return null;
        }

        Layer layer = new Layer(network, parentId, lineNum, nodeNum);
        layer = layerService.save(layer);

        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(layer, i, NodeConst.INIT_BIAS);
            nodeList.add(node);
        }
        nodeService.saveAll(nodeList);

        return layer;
    }

    /**
     * 生成层与层之间的线
     *
     * @param up      上
     * @param down    下
     * @param lineNum 线数
     * @param mode    模式
     * @return
     */
    public boolean generateLine(Layer up, Layer down, int lineNum, int mode) {
        if ((null == up) || (null == down)) {
            return false;
        }
        List<Node> upNodeList = nodeService.findAllByLayer(up);
        List<Node> downNodeList = nodeService.findAllByLayer(down);
        List<Line> lines = new ArrayList<>();
        boolean over = false;
        int count = 0;
        int upSize = upNodeList.size();
        for (int i = 0; i < upSize; i += mode) {
            for (Node downNode : downNodeList) {
                if (lineNum < 0) {
                    over = true;
                    break;
                }
                lineNum--;
                Line line = new Line(upNodeList.get(i), downNode, 1000);
                lines.add(line);
                if (count == 1000) {
                    lineService.saveAll(lines);
                    lines.clear();
                    count = 0;
                }
                count++;
            }
            if (over) {
                break;
            }
        }
        lineService.saveAll(lines);

        return true;
    }

}
