package com.heyx.jsoup.service.net;

import com.heyx.jsoup.entity.net.Network;
import com.heyx.jsoup.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class NetworkService extends BaseService<Network, String> {

    /**
     * 产生结果
     * @return
     */
    public byte[] generate(){
        return new byte[8];
    }

    /**
     * 训练
     */
    public void train(){

    }

    /**
     * 检查两个网络是否相同
     */
    public void checkNetwork(){

    }


    /**
     * 生成一个神经网络
     */
    public void generateNetWork(){

    }

}
