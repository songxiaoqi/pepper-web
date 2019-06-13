package com.song.pepper.util;

import org.springframework.lang.Nullable;

import java.util.regex.Pattern;

/**
 * author woson
 * date 2019/6/13
 * description
 */
public class StringUtils {

    /**
     * 是否是数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 去掉空格
     * @param str
     * @return
     */
    public static String trim(String str){
        return str.replaceAll(" +", "");
    }

    /**
     * 是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }
}
