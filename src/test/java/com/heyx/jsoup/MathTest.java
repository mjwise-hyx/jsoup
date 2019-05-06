package com.heyx.jsoup;

import com.heyx.jsoup.util.MathUtils;
import org.junit.Test;

//求排列数组合数
public class MathTest {

    @Test
    public void test() {
        for (int i = 1; i <= 6; i++) {
            System.out.println("A(6," + i + ")=" + MathUtils.A(6, i));
        }
        for (int i = 1; i <= 6; i++) {
            System.out.println("C(6," + i + ")=" + MathUtils.C(6, i));
        }
        System.out.println("C(6,5)=" + MathUtils.C(6, 5));// 6
    }

    @Test
    public void getCode(){
        String code = "03081";
        String result = MathUtils.getCode(code, 3);
        System.out.println(result);
    }

}
