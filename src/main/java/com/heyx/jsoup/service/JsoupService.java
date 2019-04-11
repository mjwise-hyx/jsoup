package com.heyx.jsoup.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:39
 * @email; 1064042411@qq.com
 */
@Service
public class JsoupService {

    public Elements parseDocumentToURl(Document doc){
        assert doc != null;
        Element body = doc.body();
        Elements elements = body.getElementsByClass("iSelectList");
        return elements.select("a");
    }


    public void parseDocument(Document doc){
        assert doc != null;
        Element body = doc.body();
        Elements element = body.getElementsByClass("kj_tablelist02");
        Elements tbody = element.select("table").select("tbody");
        Elements trs = tbody.select("table").select("tr");
        Element tr1 = trs.first();
        Element tr2 = trs.last();

        System.out.println(getNameByTR(tr1).get(0));
        System.out.println(tr2.select("td").last().text());
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
