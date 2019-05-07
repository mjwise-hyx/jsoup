package com.heyx.jsoup.ctr;

import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.net.NetworkService;
import com.heyx.jsoup.service.net.NetworkThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    NetworkService networkService;

    @GetMapping(value = {"/", "/index"})
    public String index(){
        Optional<Network> oNetwork = networkService.findById("402881e56a8fe65e016a8fe672a50000");
        if (oNetwork.isPresent()) {
            NetworkThread networkThread = new NetworkThread(oNetwork.get(), networkService , "18100");
            networkThread.start();
        }

        return "view/index";
    }
}
