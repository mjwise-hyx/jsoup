package com.heyx.jsoup.ctr;

import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.net.NetworkService;
import com.heyx.jsoup.service.net.NetworkThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class ViewController {

    @Autowired
    NetworkService networkService;

    @GetMapping(value = {"/", "/index"})
    public String index() throws InterruptedException {
        Optional<Network> oNetwork = networkService.findById("402881e56a8fe65e016a8fe672a50000");
        if (oNetwork.isPresent()) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            Callable<String> callable = new NetworkThread(oNetwork.get(), networkService, "18100");
            Future future = executorService.submit(callable);
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "view/index";
    }
}
