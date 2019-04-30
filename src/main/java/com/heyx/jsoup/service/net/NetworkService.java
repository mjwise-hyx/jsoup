package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.NetworkRepo;
import com.heyx.jsoup.entity.net.Layer;
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
        List<Layer> m_layer = layerService.findAllByNetwork(m);
        List<Layer> n_layer = layerService.findAllByNetwork(n);

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
