package com.heyx.jsoup;

import com.heyx.jsoup.entity.Address;
import com.heyx.jsoup.entity.Socks;
import com.heyx.jsoup.service.AddressService;
import com.heyx.jsoup.service.JsoupService;
import com.heyx.jsoup.service.SocksSerivce;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    JsoupService jsoupService;

    @Autowired
    SocksSerivce socksSerivce;

    @Autowired
    AddressService addressService;

    @Test
    public void contextLoads() throws IOException {
        Document doc = Jsoup.connect("http://kaijiang.500.com/ssq.shtml").get();
        jsoupService.parseDocument(doc);
        jsoupService.parseDocumentToURl(doc);
    }

    @Test
    public void getAllAddress() throws IOException {
        Document doc = Jsoup.connect("http://kaijiang.500.com/ssq.shtml").get();
        Elements aList = jsoupService.parseDocumentToURl(doc);
        for (Element element : aList) {
            String url = element.attr("href");
            String text = element.text();
            Address address = new Address(url, text);
            address = addressService.save(address);
            System.out.println(address);
        }
    }


    @Test
    public void addSocks(){
        Socks socks = new Socks("定时任务一", "0/5 * * * * ?");
        socks = socksSerivce.save(socks);
        System.out.println(socks);
    }

}
