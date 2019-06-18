package com.song.pepper.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileUtils {

    public static void writeLine(String fileName,String text){
        File file = new File(Thread.currentThread().getContextClassLoader().getResource(fileName).getFile());
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,true);
            fos.write((text).getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (Exception e) {
        }
    }
}
