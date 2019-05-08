package com.heyx.jsoup.service.net;

import com.heyx.jsoup.entity.net.Network;


/**
 * 用于训练的线程类
 */
public class NetworkThread extends Thread {

    private String startCode;

    private Network network;

    private NetworkService networkService;

    private String good;

    public NetworkThread(Network network,
                         NetworkService networkService,
                         String startCode,
                         String good){
        this.network = network;
        this.networkService = networkService;
        this.startCode = startCode;
        this.good = good;
    }

    @Override
    public void run() {
        networkService.train(network, startCode, good);
    }
}
