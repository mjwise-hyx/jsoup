package com.heyx.jsoup.service.net;

import com.heyx.jsoup.dao.net.NodeRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService extends BaseService<Node, String> {
    @Autowired
    NodeRepo nodeRepo;

    public List<Node> findAllByLayer(Layer layer) {
        return nodeRepo.findAllByLayerOrderBySize(layer);
    }
}
