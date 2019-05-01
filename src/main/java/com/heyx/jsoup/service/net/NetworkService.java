package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.NetworkRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Line;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkService extends BaseService<Network, String> {

    @Autowired
    NetworkRepo networkRepo;

    @Autowired
    NodeService nodeService;

    @Autowired
    LayerService layerService;

    @Autowired
    LineService lineService;

    /**
     * 产生结果
     * @return
     */
    public byte[] generate(){
        return new byte[8];
    }

    /**
     * 训练
     */
    public void train(){
        //TODO
    }

    /**
     * 检查两个网络是否相同
     */
    public boolean checkNetwork(Network m, Network n){
        if ((null == m) || (null == n)){
            return false;
        }
        if (!m.getLayerNum().equals(n.getLayerNum())){
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
            if (m_line_num != n_line_num){
                return false;
            }

            int m_node_num = m_layer.get(i).getNodeNum();
            int n_node_num = n_layer.get(i).getNodeNum();
            if (m_node_num != n_node_num){
                return false;
            }
        }

        return true;
    }

    /**
     * 生成一个神经网络
     */
    public void generateNetWork(){

    }

    /**
     * 生成输出层节点
     * 输出层
     */
    public List<Node> generateOutput(Network network){
        if (network == null){
            return null;
        }
        //TODO
        int nodeNum = NodeConst.MIN_NODE_NUM;
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node();
            nodeList.add(node);
        }
        return nodeService.saveAll(nodeList);
    }

    /**
     * 生成输入层节点
     * 根据 history值来算
     */
    public List<Node> generateInput(Network network, int historyNum){
        if(network == null){
            return null;
        }
        int nodeNum = NodeConst.MIN_NODE_NUM * historyNum;
        Layer layer = new Layer(network, LayerConst.INPUT_LAYER_ID, 0, nodeNum);
        layer = layerService.save(layer);
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(layer, i, NodeConst.INIT_BIAS);
            nodeList.add(node);
        }
        int layerNum = network.getLayerNum() + 1;
        network.setLayerNum(layerNum);
        networkRepo.save(network);
        return nodeService.saveAll(nodeList);
    }

}
