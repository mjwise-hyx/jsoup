package com.heyx.jsoup;

import com.heyx.jsoup.entity.dot.Address;
import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.entity.dot.Socks;
import com.heyx.jsoup.service.dot.AddressService;
import com.heyx.jsoup.service.dot.HistoryService;
import com.heyx.jsoup.service.dot.JsoupService;
import com.heyx.jsoup.service.dot.SocksSerivce;
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

    @Autowired
    HistoryService historyService;

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
            if (!historyService.existsByCode(address.getCode())){
                Document doc = Jsoup.connect(address.getUrl()).get();
                List<String> strings = jsoupService.parseDocument(doc);
                if (7 == strings.size()){
                    History history = new History();
                    history.setCode(address.getCode());
                    history.setBule(strings.get(0));
                    history.setNum1(strings.get(1));
                    history.setNum2(strings.get(2));
                    history.setNum3(strings.get(3));
                    history.setNum4(strings.get(4));
                    history.setNum5(strings.get(5));
                    history.setNum6(strings.get(6));

                    historyService.save(history);
                }

            }

        }
    }


    @Test
    public void addSocks(){
        Socks socks = new Socks("定时任务一", "0/5 * * * * ?");
        socks = socksSerivce.save(socks);
        System.out.println(socks);
    }

    @Test
    public void checkCode(){
        List<Address> addresses = addressService.findAll();
        for (Address address : addresses) {
            if (!historyService.existsByCode(address.getCode())){
                System.out.println(address.getCode());
            }
        }
    }

    @Test
    public void convertHistory(){

        List<History> histories = historyService.findAll();
        for (History history : histories) {
            System.out.println(history.toString());
        }
    }

}
