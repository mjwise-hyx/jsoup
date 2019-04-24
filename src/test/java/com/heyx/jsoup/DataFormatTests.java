package com.heyx.jsoup;

import com.heyx.jsoup.entity.Address;
import com.heyx.jsoup.entity.History;
import com.heyx.jsoup.service.HistoryService;
import com.heyx.jsoup.util.FormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-24 15:59
 * @email; 1064042411@qq.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataFormatTests {

    @Autowired
    HistoryService historyService;

    @Test
    public void convertHistory() {
        List<History> histories = historyService.findAll();

        Optional<History> historyOptional = historyService.findById("402881e56a20c341016a20c360bc001c");
        if (historyOptional.isPresent()){
            byte[][] his = historyService.convertToMatrix( historyOptional.get());
            System.out.println(FormatUtils.printBytes(his));

            byte[] simple = historyService.convertToSampleMatrix(historyOptional.get());
            System.out.println(FormatUtils.bytesTobit(simple,false));
        }
//        for (int i = 0; i < 1; i++) {
//            byte[][] his = historyService.convertToMatrix(histories.get(i));
//
//            System.out.println(FormatUtils.printBytes(his));
//        }

    }

}
