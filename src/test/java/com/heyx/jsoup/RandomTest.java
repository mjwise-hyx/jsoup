package com.heyx.jsoup;

import com.heyx.jsoup.constant.NodeConst;
import org.junit.Test;

import java.util.Random;

/**
 * 一、思路：
 * 1、生成随机数需要使用到Java工具类中的Random类。
 * 2、要求是随机x到y之间的整数，即指定范围，则使用Random类中的nextInt(int n)方法。
 * 3、该方法生成从0（包括）到n（不包括）之间的随机整数，是一个伪随机数，并不是真正的随机数。
 * 4、若x不为0，则需要在随机结果后加上x。参数n的值也需要加上1后减去x。最后结果才符合要求的范围。
 * 二、实现：
 * 1、定义x和y的值，修改该值可以随机不同范围的整数。
 * 2、调用Random中的nextInt(int n)方法，计算随机数。
 * 3、将结果打印到控制台。
 * */
public class RandomTest {

    private static final int START = -NodeConst.LINE_FACTOR_NUM;   //定义范围开始数字

    private static final int END = NodeConst.LINE_FACTOR_NUM; //定义范围结束数字

    @Test
    public void getRendom() {

        //创建Random类对象
        Random random = new Random();
        //产生随机数
        int number = random.nextInt(END - START + 1) + START;
        double generatorDouble = START +  new Random().nextDouble() * (END - START);
        //打印随机数
        System.out.println("产生一个"+START+"到"+END+"之间的随机整数："+ number);
        System.out.println("产生一个"+START+"到"+END+"之间的double随机整数："+ generatorDouble);

    }

    @Test
    public void getNodeNum(){
        for (int nodeNum = NodeConst.MIN_NODE_NUM; nodeNum <= NodeConst.MAX_NODE_NUM; nodeNum+=NodeConst.MIN_NODE_NUM) {
            System.out.println(nodeNum);
        }
    }

}