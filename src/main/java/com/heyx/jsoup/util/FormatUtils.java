package com.heyx.jsoup.util;

import java.math.BigInteger;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-24 16:11
 * @email; 1064042411@qq.com
 */
public class FormatUtils {

    public static byte[] byteMergerAll(byte[]... values) {
        int byte_length = 0;
        for (int i = 0; i < values.length; i++) {
            byte_length += values[i].length;
        }
        byte[] all_byte = new byte[byte_length];
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, count, b.length);
            count += b.length;
        }
        return all_byte;
    }

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
            result.append(bytesTobit(b));
            result.append("\n");
        }
        return result.toString();
    }

    public static String bytesTobit(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(byteToBit(b));
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

    public static int getIndexValue(String src, int index) {
        return Integer.parseInt(src.substring(index, index + 1));
    }


}
