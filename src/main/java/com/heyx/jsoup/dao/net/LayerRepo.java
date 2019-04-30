package com.heyx.jsoup.dao.net;

import com.heyx.jsoup.dao.BaseRepo;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Network;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayerRepo extends BaseRepo<Layer, String> {
    List<Layer> findAllByNetwork(Network network);
}
