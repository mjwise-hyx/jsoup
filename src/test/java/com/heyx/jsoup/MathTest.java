package com.heyx.jsoup;

import com.heyx.jsoup.util.MathUtils;
import org.junit.Test;
import org.opencv.core.Mat;

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
    public void getCodeTest(){
        String code = "03081";
        String result = MathUtils.getCode(code, 3);
        System.out.println(result);
    }

    @Test
    public void sigmoidTest(){
        System.out.println(MathUtils.sigmoid(10.0));
        System.out.println(MathUtils.sigmoidDerivative(0.9));
    }

    @Test
    public void logTest(){
        System.out.println(Math.log(2.0));
    }
}
