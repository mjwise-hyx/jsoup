package com.heyx.jsoup.lesson1;

import org.junit.Test;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-15 19:45
 * @email; 1064042411@qq.com
 */
public class Nd4jGetAndSetParts {

    @Test
    public void getInitArray(){
        INDArray nd = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, new int[]{2, 6});
        System.out.println("原始数组");
        System.out.println(nd);
    }
}
