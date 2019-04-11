package com.heyx.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-09 19:54
 * @email; 1064042411@qq.com
 */
public class JsoupTest {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("http://kaijiang.500.com/ssq.shtml").get();
        Element body = doc.body();
        Elements element = body.getElementsByClass("kj_tablelist02");
        Elements tbody = element.select("table").select("tbody");
        Elements trs = tbody.select("table").select("tr");
        Element tr1 = trs.first();
        Element tr2 = trs.last();
        List<String> nameList =getNameByTR(tr1);
        System.out.println(nameList.get(0));
        System.out.println(tr2.select("td").last().text());
//        List<String> names = new ArrayList<>();
//        for (Element tr : trs) {
//            System.out.println("tr ===========" + tr);
////            names.addAll(getNameByClass(tr));
//        }
//        System.out.println(names);
    }

    private List<String> getNameByClass(Element element){
        List<String> nameList = new ArrayList<>();

        Elements reds = element.getElementsByClass("ball_red");
        Elements blues = element.getElementsByClass("ball_blue");
        for (Element red : reds) {
            nameList.add(red.text());
        }
        for (Element blue : blues) {
            nameList.add(blue.text());
        }
        System.out.println(nameList);
        return nameList;
    }

    private List<String> getNameByTR(Element element){
        List<String> nameList = new ArrayList<>();
        Elements blues = element.getElementsByClass("ball_blue");
        for (Element blue : blues) {
            nameList.add(blue.text());
        }

        return nameList;
    }
}
