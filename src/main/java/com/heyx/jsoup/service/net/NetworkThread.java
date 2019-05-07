package com.heyx.jsoup.service.net;

import com.heyx.jsoup.entity.net.Network;

/**
 * 用于训练的线程类
 */
public class NetworkThread extends Thread {

    private String startCode;

    private Network network;

    private NetworkService networkService;

    public NetworkThread(Network network, NetworkService networkService, String startCode){
        this.network = network;
        this.networkService = networkService;
        this.startCode = startCode;
    }

    @Override
    public void run() {
        String result = networkService.calc(network, startCode);
        System.out.println(result);
    }
}
