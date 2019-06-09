package com.heyx.jsoup.ctr;

import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.dot.HistoryService;
import com.heyx.jsoup.service.net.LayerService;
import com.heyx.jsoup.service.net.NetworkCall;
import com.heyx.jsoup.service.net.NetworkService;
import com.heyx.jsoup.service.net.NetworkThread;
import com.heyx.jsoup.util.FormatUtils;
import com.heyx.jsoup.util.MathUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
//        Optional<Network> oNetwork = networkService.findById("402881e56a8fe65e016a8fe672a50000");
//        if (oNetwork.isPresent()) {
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            Callable<String> callable = new NetworkCall(oNetwork.get(), networkService, "18100");
//            Future future = executorService.submit(callable);
//            try {
//                System.out.println(future.get());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        List<Network> networkList = networkService.findAll();
        for (Network network : networkList) {
            String code = "03001";
            NetworkThread networkThread = new NetworkThread(network, networkService, code);
            networkThread.start();
        }
        return "view/index";
    }
}
