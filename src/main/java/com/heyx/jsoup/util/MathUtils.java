package com.heyx.jsoup.util;

public class MathUtils {

    public static String getCode(String code, int step){
        int codeLength = code.length();
        StringBuilder result = new StringBuilder(Integer.toString(Integer.valueOf(code) + step - 1));

        while (result.length() < codeLength){
            result.insert(0, "0");
        }
        return result.toString();
    }

    // 求排列数 A(n,m) n>m
    public static int A(int n, int m)
    {
        int result = 1;
        // 循环m次,如A(6,2)需要循环2次，6*5
        for (int i = m; i > 0; i--)
        {
            result *= n;
            n--;// 下一次减一
        }
        return result;
    }

    // 求组合数，这个也不需要了。定义式，不使用互补率
    public static int C2(int n, int m)
    {
        // int denominator=factorial(up);//分母up的阶乘
        // 分母
        int denominator = A(m, m);// A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        // 分子
        int numerator = A(n, m);// 分子的排列数
        return numerator / denominator;
    }

    // 应用组合数的互补率简化计算量
    public static int C(int n, int m) {
        int helf = n / 2;
        if (m > helf)
        {
            System.out.print(m + "---->");
            m = n - m;
            System.out.print(m + "\n");
        }
        // 分子的排列数
        int numerator = A(n, m);
        // 分母的排列数
        int denominator = A(m, m);
        return numerator / denominator;
    }
}
