package com.heyx.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-09 19:54
 * @email; 1064042411@qq.com
 */
public class JsoupTest {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("http://www.baidu.com").get();
        String title = doc.title();
        System.out.println("title is: " + title);
    }
}
