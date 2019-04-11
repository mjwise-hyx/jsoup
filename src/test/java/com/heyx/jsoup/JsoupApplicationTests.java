package com.heyx.jsoup;

import com.heyx.jsoup.service.BaseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupApplicationTests {

    @Autowired
    BaseService baseService;

    @Test
    public void contextLoads() throws IOException {
        Document doc = Jsoup.connect("http://kaijiang.500.com/ssq.shtml").get();

        baseService.parseDocument(doc);
    }

}
