package com.heyx.jsoup.constant;

public class NodeConst {

    /**
     * 默认偏置
     */
    public static final Integer INIT_BIAS = 1;

    /**
     * 单层最少节点数
     */
    public static final int MIN_NODE_NUM = 56;

    /**
     * 单层最大节点数
     */
    public static final int MAX_NODE_NUM = 56 * 100;

    /**
     * 输入层 最大输入 数据 个数
     */
    public static final int MAX_INPUT_NUM = 56 * 10;

    /**
     * 放大倍数
     */
    public static final int LINE_FACTOR_NUM = 1000;

    /**
     * 最大偏置
     */
    public static final int MAX = 1000;

    /**
     * 输出阈值
     */
    public static final Double LINE_OUTPUT_THRESHOLD = 0.5;
    public static final int ERROR_NODE_RATE = 5;
}
