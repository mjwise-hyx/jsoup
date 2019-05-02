package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.InfoRepo;
import com.heyx.jsoup.entity.net.Info;
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

            for (int l_index = 0; l_index < layerNum; l_index++) {
                //输入层
                //中间层
                //输出层
                for (int nodeNum = NodeConst.MIN_NODE_NUM; nodeNum < NodeConst.MAX_NODE_NUM; nodeNum+=56) {
                    System.out.println(nodeNum);
                }
            }
        }
    }

}
