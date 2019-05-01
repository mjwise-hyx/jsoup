package com.heyx.jsoup;

import com.heyx.jsoup.constant.LayerConst;
import com.heyx.jsoup.entity.net.Layer;
import com.heyx.jsoup.service.net.LayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LayerTest {

    @Autowired
    LayerService layerService;

    @Test
    public void sortTest(){
        Layer layer = new Layer();
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();

        layerService.save(layer);
        layerService.save(layer1);
        layerService.save(layer2);

        layer.setLineNum(1);
        layer1.setLineNum(2);
        layer2.setLineNum(3);
        layer.setParentId(layer1.getId());
        layer1.setParentId(layer2.getId());
        layer2.setParentId(LayerConst.INPUT_LAYER_ID);

        List<Layer> layerList = new ArrayList<>();
        layerList.add(layer);
        layerList.add(layer1);
        layerList.add(layer2);
        layerService.saveAll(layerList);


        List<Layer> layerList1 = layerService.findAll();
        for (Layer layer3 : layerList1) {
            System.out.println( "-----" + layer3.toString());
        }

        List<Layer> layerList2 = layerService.sortByParentId(layerList1);
        for (Layer layer4 : layerList2) {
            System.out.println( "======" + layer4.toString());
        }
    }

    @Test
    public void deleteLayer(){
        layerService.deleteAll();
    }
}
