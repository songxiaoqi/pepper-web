package com.song.pepper.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 身份证工具类
 */
public class IDCardUtils {

    public Object card(String card) throws Exception {
        card = StringUtils.trim(card);
        if(!StringUtils.isEmpty(card)&&card.length()==17&&StringUtils.isInteger(card)){
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("card.txt").getFile());
            if(!file.exists()){
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file,true);
            fos.write((card+verifyId(card)).getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
            return card+verifyId(card);
        }
        return 1;
    }

    /**
     * 计算校验位
     * @param id
     * @return
     */
    public static String verifyId(String id) {
        int count = 0;
        char[] charArr = id.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            int n = Integer.parseInt(charArr[i] + "");
            count += n * (Math.pow(2, 17 - i) % 11);
        }
        switch (count % 11) {
            case 0: return "1";
            case 1: return "0";
            case 2: return "X";
            default:
                return 12 - (count % 11) + "";
        }
    }
}
