package com.heyx.jsoup.dao.net;

import com.heyx.jsoup.dao.BaseRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Node;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepo extends BaseRepo<Node, String> {

    List<Node> findAllByLayerOrderBySize(Layer layer);
}
