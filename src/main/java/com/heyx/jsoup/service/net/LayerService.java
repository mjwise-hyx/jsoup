package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.LayerRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.List;

@Service
public class LayerService extends BaseService<Layer, String> {

    @Autowired
    LayerRepo layerRepo;

    public List<Layer> findAllByNetwork(Network network) {
        return layerRepo.findAllByNetwork(network);
    }

    public Layer findInputByNetwork(Network network){
        return layerRepo.findFirstByNetworkAndParentId(network, LayerConst.INPUT_LAYER_ID);
    }

    public int getStepSize(Network network){
        Layer layer = findInputByNetwork(network);
        if (layer == null){
            return 0;
        }else {
            return layer.getNodeNum() / NodeConst.MIN_NODE_NUM;
        }
    }

    public List<Layer> sortByParentId(List<Layer> layers){
        String parentId = LayerConst.INPUT_LAYER_ID;
        List<Layer> layerList = new ArrayList<>();
        while (layers.size() >0){
            for (Layer layer : layers) {
                if (layer.getParentId().equals(parentId)){
                    layerList.add(layer);
                    layers.remove(layer);
                    parentId = layer.getId();
                    break;
                }
            }
        }
        return layerList;
    }
}
