package com.heyx.jsoup.util;

import java.math.BigInteger;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-24 16:11
 * @email; 1064042411@qq.com
 */
public class FormatUtils {

    public static byte convertToByte(int num) {
        return (byte) (num & 0xff);
    }

    public static byte[] intToByte(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }

    public static int byteToInt(byte b) {
        return b & 0xff;
    }

    public static int bytesToInt(byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    public static String printBytes(byte[][] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte[] b : bytes) {
            result.append(bytesTobit(b, false));
            result.append("\n");
        }
        return result.toString();
    }

    public static String printBytes(byte[][] bytes, int radix) {
        StringBuilder result = new StringBuilder();
        for (byte[] aByte : bytes) {
            result.append(binary(aByte, radix));
            result.append("\n");
        }
        return result.toString();
    }

    private static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }


    public static String bytesTobit(byte bytes[], boolean isBr) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(byteToBit(b));
            result.append(" ");
            if (isBr) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * 把byte转为字符串的bit
     */
    private static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b) & 0x1);
    }


}
