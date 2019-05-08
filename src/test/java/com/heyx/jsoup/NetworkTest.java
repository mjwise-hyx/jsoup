package com.heyx.jsoup;

import com.heyx.jsoup.entity.net.Info;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.net.*;
import com.heyx.jsoup.util.CountStringUtils;
import kotlin.jvm.Synchronized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkTest {

    @Autowired
    NodeService nodeService;

    @Autowired
    NetworkService networkService;

    @Autowired
    InfoService infoService;

    @Autowired
    LayerService layerService;

    /**
     * 先生成一个网络 用于跑通整个工程
     * 全连通网络
     * 5*56
     * 10*56
     * 8*56
     * 6*56
     * 4*56
     * 2*56
     * 1*56
     */
    @Test
    public void generateInfo() {
        String layerInfo =
                //输入层
                "0:280@1" +
                        //第一层
                        "-156880:560@1" +
                        //第二层
                        "-250880:448@1" +
                        //第三层
                        "-150528:336@1" +
                        //第四层
                        "-75264:224@1" +
                        //第五层
                        "-25088:112@1" +
                        //输出层
                        "-6272:56@1";
        Info info = new Info(7, layerInfo);
        info = infoService.save(info);
        System.out.println(info.toString());
        System.out.println(infoService.isValid(info));
    }

    @Test
    public void testParseInfo() {
        List<Info> infos = infoService.findAll();
        String layerInfo = infos.get(0).getLayerInfo();

        List<String> layers = CountStringUtils.splitString(layerInfo, "-");
        for (String layer : layers) {
            System.out.println(layer);
            System.out.println("the node Num is " + layer.split(":")[1].split("@")[0]);
        }
    }

    @Test
    public void generateNetwork() {
        List<Info> infos = infoService.findAll();
        networkService.generateNetWork(infos.get(0));
    }

    @Test
    public void findNode() {
        Optional<Layer> layerOptional = layerService.findById("40283a816a87e7cd016a87e7e5ac011a");
        if (layerOptional.isPresent()) {
            List<Node> nodes = nodeService.findAllByLayer(layerOptional.get());
            for (Node node : nodes) {
                System.out.println(node.getSize());
            }
        }

    }

    @Test
    public void calcTest() {
        Optional<Network> networkOptional = networkService.findById("402881e56a8fe65e016a8fe672a50000");
        if (networkOptional.isPresent()) {
            String result = networkService.calc(networkOptional.get(), "18100", new HashMap<>());
            System.out.println(result);
        }
    }

//    @Test
//    @Synchronized
//    public void calcThreadTest() {
//        Optional<Network> networkOptional = networkService.findById("402881e56a8fe65e016a8fe672a50000");
//        if (networkOptional.isPresent()) {
//            NetworkThread networkThread = new NetworkThread(networkOptional.get(), networkService, "18100");
//            networkThread.start();
//        }
//    }


}
