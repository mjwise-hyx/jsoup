package com.heyx.jsoup;

import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.service.dot.HistoryService;
import com.heyx.jsoup.util.FormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

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
        Optional<History> historyOptional = historyService.findById("40289fd36a0d3983016a0d3991040000");
        if (historyOptional.isPresent()){
            byte[][] his = historyService.convertToMatrix( historyOptional.get());
            System.out.println(FormatUtils.printBytes(his));
            History history1 = historyService.convertToHistory(his, "12221");
            System.out.println(history1.toString());


            byte[] simple = historyService.convertToSampleMatrix(historyOptional.get());
            String simple_format = FormatUtils.bytesTobit(simple,false).replace(" ","");
            System.out.println(simple_format.length());
            System.out.println(simple_format);

            History history = historyService.convertToHistory(simple, "1111");
            System.out.println(history.toString());
        }
    }

}
