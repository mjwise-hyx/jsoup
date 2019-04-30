package com.heyx.jsoup.service.net;

import com.heyx.jsoup.dao.net.LayerRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayerService extends BaseService<Layer, String> {

    @Autowired
    LayerRepo layerRepo;

    public List<Layer> findAllByNetwork(Network network) {
        return layerRepo.findAllByNetwork(network);
    }
}
