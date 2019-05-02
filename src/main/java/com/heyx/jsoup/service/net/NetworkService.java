package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.NetworkRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Line;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 简单检查两个网络是否相同
     */
    public boolean checkNetwork(Network m, Network n){
        if ((null == m) || (null == n)){
            return false;
        }
        return m.getInfo().equals(n.getInfo());
    }

    /**
     * 检查两个网络是否相同
     */
    @Transactional
    public boolean checkNetworkLayer(Network m, Network n){
        if ((null == m) || (null == n)){
            return false;
        }
        if (!m.getInfo().equals(n.getInfo())){
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

//        Network network = new Network();
//        network = networkRepo.save(network);

    }

    /**
     * 生成一层的节点
     * 输入层 nodeNum：NodeConst.MIN_NODE_NUM * historyNum;
     * 输出层 nodeNum：NodeConst.MIN_NODE_NUM
     */
    @Transactional
    public Layer generateLayer(Network network, String parentId , int nodeNum){
        if (network == null){
            return null;
        }
        if (nodeNum < NodeConst.MIN_NODE_NUM || nodeNum > NodeConst.MAX_NODE_NUM){
            return null;
        }
        if (StringUtils.isBlank(parentId)){
            return null;
        }

        Layer layer = new Layer(network, parentId, 0, nodeNum);
        layer = layerService.save(layer);

        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(layer, i, NodeConst.INIT_BIAS);
            nodeList.add(node);
        }
        nodeService.saveAll(nodeList);

        return layer;
    }

}
