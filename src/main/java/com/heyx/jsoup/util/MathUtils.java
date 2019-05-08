package com.heyx.jsoup.util;

public class MathUtils {


    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    public static double tanhDerivative(double value) {
        return 1 - tanh(value) * tanh(value);
    }

    public static double tanh(double value) {
        double ex = Math.pow(Math.E, value);// e^x
        double ey = Math.pow(Math.E, -value);//e^(-x)
        double sinhx = ex - ey;
        double coshx = ex + ey;
        return sinhx / coshx;
    }

    /**
     * sigmoid 求导
     */
    public static double sigmoidDerivative(double value) {
        double A = sigmoid(value);
        double B = 1 - sigmoid(value);
        return A * B;
    }

    /**
     * 激活函数
     */
    public static double sigmoid(double value) {
        double ey = Math.pow(Math.E, -value);
        return 1 / (1 + ey);
    }


    /**
     * softmax分类
     *
     * @param soft_in 输入数据
     * @param theta   训练参数
     * @return label[n][1]
     * @author 后一个X前一个：θ[n][m] * x[m][1]
     * n个标签
     */
    public static double[] softmax(double[] soft_in, double[][] theta) {
        if (theta[0].length != soft_in.length) {
            System.out.println("输入矩阵不匹配，无法进行矩阵运算");
            return null;
        }
        double[] result = new double[theta.length];//返回标签
        double[] value = new double[theta.length];// 分子
        double denominator = 0.0;//分母
        for (int i = 0; i < result.length; i++) {
            double temValue = 0.0;
            for (int j = 0; j < soft_in.length; j++) {
                temValue += theta[i][j] * soft_in[j];
            }
            value[i] = Math.pow(Math.E, temValue);
            denominator += Math.pow(Math.E, temValue);
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = value[i] / denominator;
        }
        return result;
    }

    /**
     * history 处理字符编码
     */
    public static String getCode(String code, int step) {
        int codeLength = code.length();
        StringBuilder result = new StringBuilder(Integer.toString(Integer.valueOf(code) + step - 1));

        while (result.length() < codeLength) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    // 求排列数 A(n,m) n>m
    public static int A(int n, int m) {
        int result = 1;
        // 循环m次,如A(6,2)需要循环2次，6*5
        for (int i = m; i > 0; i--) {
            result *= n;
            n--;// 下一次减一
        }
        return result;
    }

    // 求组合数，这个也不需要了。定义式，不使用互补率
    public static int C2(int n, int m) {
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
        if (m > helf) {
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
