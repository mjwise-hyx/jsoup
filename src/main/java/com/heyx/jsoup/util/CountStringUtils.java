package com.heyx.jsoup.util;

public class CountStringUtils {

    public static int count(String src, char ch){
        int count = 0;
        char[] chars = src.toCharArray();
        for (char aChar : chars) {
            if (ch == aChar) {
                count++;
            }
        }
        return count;
    }

}
