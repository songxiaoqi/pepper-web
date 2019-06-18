package com.song.pepper.controller;

import com.song.pepper.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/pepper")
public class PepperController {


    /**
     * 身份证校验页面
     * @return
     */
    @RequestMapping(value="/vue",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView vueTest(){
        return new ModelAndView("pepper");
    }

    /**
     * 身份证校验位计算
     * @param card
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/card",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
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
    public String verifyId(String id) {
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

    @RequestMapping("download")
    public String downLoad(HttpServletResponse response){
        String filename="2.jpg";
        String filePath = "F:/test" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    @GetMapping("/upload")
    public ModelAndView upload(HttpServletRequest request) throws Exception {
        return new ModelAndView("upload");
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = new File("").getAbsolutePath();
        File dest = new File(filePath+"\\" + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
        }
        return "上传失败！";
    }
}
