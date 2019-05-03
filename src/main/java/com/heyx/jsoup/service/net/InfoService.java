package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.InfoRepo;
import com.heyx.jsoup.entity.net.Info;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService extends BaseService<Info, String> {

    @Autowired
    InfoRepo infoRepo;

    /**
     * 原则：
     * 1.不允许出现网络中的孤立节点 所以边数 大于 点数 - 1
     * 2. 输出层必须是 56 个节点
     * 3. 层数 >= 3
     */
    public void generateInfo(){
        for (int layerNum = LayerConst.MIN_LAYER_NUM; layerNum <= LayerConst.MAX_LAYER_NUM; layerNum++) {
            String info = "";
            for (int l_index = 0; l_index < layerNum; l_index++) {
                //输入层
                for (int inputNum = NodeConst.MIN_NODE_NUM; inputNum < NodeConst.MAX_INPUT_NUM; inputNum+=NodeConst.MIN_NODE_NUM) {
                    //中间层
                    for (int nodeNum = NodeConst.MIN_NODE_NUM; nodeNum < NodeConst.MAX_NODE_NUM; nodeNum+=NodeConst.MIN_NODE_NUM) {
                        System.out.println(nodeNum);
                        //输出层
                    }
                }
            }
        }
    }

}
