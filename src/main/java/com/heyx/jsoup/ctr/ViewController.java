package com.heyx.jsoup.ctr;

import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.dot.HistoryService;
import com.heyx.jsoup.service.net.LayerService;
import com.heyx.jsoup.service.net.NetworkService;
import com.heyx.jsoup.service.net.NetworkThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ViewController {

    @Autowired
    NetworkService networkService;

    @Autowired
    HistoryService historyService;

    @Autowired
    LayerService layerService;

    @GetMapping(value = {"/", "/index"})
    public String index() throws InterruptedException {
        List<Network> networkList = networkService.findAll();
        for (Network network : networkList) {
            String code = "03001";
            NetworkThread networkThread = new NetworkThread(network, networkService, code);
            networkThread.start();
        }
        return "view/index";
    }
}
