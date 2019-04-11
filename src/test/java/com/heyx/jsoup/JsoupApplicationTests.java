package com.heyx.jsoup;

import com.heyx.jsoup.entity.Address;
import com.heyx.jsoup.entity.History;
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

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

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
        List<String>strings = jsoupService.parseDocument(doc);
//        jsoupService.parseDocumentToURl(doc);
        System.out.println(strings);
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
    public void addHistory() throws IOException {
        List<Address> addressList = addressService.findAll();
        for (Address address : addressList) {
            Document doc = Jsoup.connect(address.getUrl()).get();
            List<String> strings = jsoupService.parseDocument(doc);
            History history = new History();
            history.setBule(strings.get(0));
            history.setNum1(strings.get(1));
            history.setNum2(strings.get(2));
            history.setNum3(strings.get(3));
            history.setNum4(strings.get(4));
            history.setNum5(strings.get(5));
            history.setNum6(strings.get(5));
            history.setNum7(strings.get(7));
            System.out.println(history);
        }
    }


    @Test
    public void addSocks(){
        Socks socks = new Socks("定时任务一", "0/5 * * * * ?");
        socks = socksSerivce.save(socks);
        System.out.println(socks);
    }

}
