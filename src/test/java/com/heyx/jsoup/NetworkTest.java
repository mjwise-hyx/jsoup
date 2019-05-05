package com.heyx.jsoup;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.entity.net.Info;
import com.heyx.jsoup.service.net.InfoService;
import com.heyx.jsoup.service.net.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkTest {

    @Autowired
    NodeService nodeService;

    @Autowired
    InfoService infoService;

    /**
     *先生成一个网络 用于跑通整个工程
     * 全连通网络
     * 1*56
     * 3*56
     * 5*56
     * 6*56
     * 4*56
     * 2*56
     * 1*56
     */
    @Test
    public void generateInfo(){
        String layerInfo =
            //输入层
            "0:56@1" +
            //第一层
            "-9408:168@1" +
            //第二层
            "-47040:280@1" +
            //第三层
            "-94080:336@1" +
            //第四层
            "-75264:224@1" +
            //第五层
            "-25088:112@1" +
            //输出层
            "-6272:56@1";
        Info info = new Info(7, layerInfo);
        System.out.println(info.toString());
        info = infoService.save(info);
        System.out.println(infoService.isValid(info));
    }



}
