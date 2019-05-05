package com.heyx.jsoup.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public static List<String> splitString (String src, String regex){
        return new ArrayList<String>(Arrays.asList(src.split(regex))) ;
    }


}
