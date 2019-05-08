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
        List<History> histories = historyService.findAllByCodeBetween("18100","18108");
        for (History history : histories) {
            byte[][] his = historyService.convertToMatrix(history);
            System.out.println(FormatUtils.printBytes(his));
            History history1 = historyService.convertToHistory(his, "12221");
            System.out.println(history1.toString());


            byte[] simple = historyService.convertToSampleMatrix(history);
            String simple_format = FormatUtils.bytesTobit(simple);
            System.out.println(simple_format.length());
            System.out.println(simple_format);

            History history2 = historyService.convertToHistory(simple, "1111");
            System.out.println(history2.toString());
        }

    }


    @Test
    public void testCompare(){
        String result = "00000000000000000000000000000000000000000000000000000000";
        String good = "00000000000000000000000000000000000000000000000000000000";
//        String good = "00011001000001100000001100000010000111010001001100000001";
        int rate = historyService.compareResult(result, good);
        System.out.println(rate);
    }

}
