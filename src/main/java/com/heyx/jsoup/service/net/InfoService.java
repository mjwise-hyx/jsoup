package com.heyx.jsoup.service.net;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.net.InfoRepo;
import com.heyx.jsoup.entity.net.Info;
import com.heyx.jsoup.service.BaseService;
import com.heyx.jsoup.util.CountStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService extends BaseService<Info, String> {

    @Autowired
    InfoRepo infoRepo;

    /**
     * 验证info是否有效
     */
    public boolean isValid(Info info){
        if (null == info){
            return false;
        }
        Integer layerNum = info.getLayerNum();
        if (null == layerNum){
            return false;
        }
        String layerInfo = info.getLayerInfo();
        if (StringUtils.isBlank(layerInfo)){
            return false;
        }
        return CountStringUtils.count(layerInfo, '-') == layerNum - 1;
    }

    /**
     * 获取所有节点数
     */
    public int getNodeNum(Info info){
        int nodeNum = 0;
        if (null == info){
            return nodeNum;
        }
        List<String> layers = CountStringUtils.splitString(info.getLayerInfo(),"-");
        for (String layer : layers) {
            nodeNum += Integer.parseInt(layer.split(":")[1].split("@")[0]);
        }
        return nodeNum;
    }

    /**
     * 原则：
     * 1.不允许出现网络中的孤立节点 所以边数 大于 点数 - 1
     * 2. 输出层必须是 56 个节点
     * 3. 层数 >= 3
     */
    public void generateInfo(){
        //先定义层数
        for (int layerNum = LayerConst.MIN_LAYER_NUM; layerNum <= LayerConst.MAX_LAYER_NUM; layerNum++) {
            String info = "";
            //输入层
            for (int inputNum = NodeConst.MIN_NODE_NUM; inputNum < NodeConst.MAX_INPUT_NUM; inputNum+=NodeConst.MIN_NODE_NUM) {


            }
            //中间层
            for (int l_index = 1; l_index < layerNum; l_index++) {
                for (int nodeNum = NodeConst.MIN_NODE_NUM; nodeNum < NodeConst.MAX_NODE_NUM; nodeNum+=NodeConst.MIN_NODE_NUM) {
                    System.out.println(nodeNum);
                    //输出层
                }
            }
        }
    }



}
