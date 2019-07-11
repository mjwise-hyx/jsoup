package com.heyx.jsoup.service.net;

import com.heyx.jsoup.entity.net.Network;
import java.util.concurrent.Callable;

/**
 * 用于训练的线程类
 */
public class NetworkCall implements Callable<String> {

    private String startCode;

    private Network network;

    private NetworkService networkService;

    public NetworkCall(Network network, NetworkService networkService, String startCode){
        this.network = network;
        this.networkService = networkService;
        this.startCode = startCode;
    }

    @Override
    public String call() throws Exception {
        return "";
    }
}
