package com.song.pepper.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * excel工具类
 */
public class ExcelUtils {

//        /**
//         * 方法名：exportExcel
//         * 功能：导出Excel
//         * 描述：
//         * 创建人：typ
//         * 创建时间：2018/10/19 16:00
//         * 修改人：
//         * 修改描述：
//         * 修改时间：
//         */
//        public static void exportExcel(HttpServletResponse response, ExcelData data) {
//            System.out.println(("导出解析开始，fileName:{}",data.getFileName());
//            try {
//                //实例化HSSFWorkbook
//                HSSFWorkbook workbook = new HSSFWorkbook();
//                //创建一个Excel表单，参数为sheet的名字
//                HSSFSheet sheet = workbook.createSheet("sheet");
//                //设置表头
//                setTitle(workbook, sheet, data.getHead());
//                //设置单元格并赋值
//                setData(sheet, data.getData());
//                //设置浏览器下载
//                setBrowser(response, workbook, data.getFileName());
//                log.info("导出解析成功!");
//            } catch (Exception e) {
//                log.info("导出解析失败!");
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * 方法名：setTitle
//         * 功能：设置表头
//         * 描述：
//         * 创建人：typ
//         * 创建时间：2018/10/19 10:20
//         * 修改人：
//         * 修改描述：
//         * 修改时间：
//         */
//        private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
//            try {
//                HSSFRow row = sheet.createRow(0);
//                //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//                for (int i = 0; i <= str.length; i++) {
//                    sheet.setColumnWidth(i, 15 * 256);
//                }
//                //设置为居中加粗,格式化时间格式
//                HSSFCellStyle style = workbook.createCellStyle();
//                HSSFFont font = workbook.createFont();
//                font.setBold(true);
//                style.setFont(font);
//                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//                //创建表头名称
//                HSSFCell cell;
//                for (int j = 0; j < str.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellValue(str[j]);
//                    cell.setCellStyle(style);
//                }
//            } catch (Exception e) {
//                log.info("导出时设置表头失败！");
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * 方法名：setData
//         * 功能：表格赋值
//         * 描述：
//         * 创建人：typ
//         * 创建时间：2018/10/19 16:11
//         * 修改人：
//         * 修改描述：
//         * 修改时间：
//         */
//        private static void setData(HSSFSheet sheet, List<String[]> data) {
//            try{
//                int rowNum = 1;
//                for (int i = 0; i < data.size(); i++) {
//                    HSSFRow row = sheet.createRow(rowNum);
//                    for (int j = 0; j < data.get(i).length; j++) {
//                        row.createCell(j).setCellValue(data.get(i)[j]);
//                    }
//                    rowNum++;
//                }
//                log.info("表格赋值成功！");
//            }catch (Exception e){
//                log.info("表格赋值失败！");
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * 方法名：setBrowser
//         * 功能：使用浏览器下载
//         * 描述：
//         * 创建人：typ
//         * 创建时间：2018/10/19 16:20
//         * 修改人：
//         * 修改描述：
//         * 修改时间：
//         */
//        private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
//            try {
//                //清空response
//                response.reset();
//                //设置response的Header
//                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//                OutputStream os = new BufferedOutputStream(response.getOutputStream());
//                response.setContentType("application/vnd.ms-excel;charset=gb2312");
//                //将excel写入到输出流中
//                workbook.write(os);
//                os.flush();
//                os.close();
//                log.info("设置浏览器下载成功！");
//            } catch (Exception e) {
//                log.info("设置浏览器下载失败！");
//                e.printStackTrace();
//            }
//
//        }
//
//
//        /**
//         * 方法名：importExcel
//         * 功能：导入
//         * 描述：
//         * 创建人：typ
//         * 创建时间：2018/10/19 11:45
//         * 修改人：
//         * 修改描述：
//         * 修改时间：
//         */
//        public static List<Object[]> importExcel(String fileName) {
//            log.info("导入解析开始，fileName:{}",fileName);
//            try {
//                List<Object[]> list = new ArrayList<>();
//                InputStream inputStream = new FileInputStream(fileName);
//                Workbook workbook = WorkbookFactory.create(inputStream);
//                Sheet sheet = workbook.getSheetAt(0);
//                //获取sheet的行数
//                int rows = sheet.getPhysicalNumberOfRows();
//                for (int i = 0; i < rows; i++) {
//                    //过滤表头行
//                    if (i == 0) {
//                        continue;
//                    }
//                    //获取当前行的数据
//                    Row row = sheet.getRow(i);
//                    Object[] objects = new Object[row.getPhysicalNumberOfCells()];
//                    int index = 0;
//                    for (Cell cell : row) {
//                        if (cell.getCellType().equals(NUMERIC)) {
//                            objects[index] = (int) cell.getNumericCellValue();
//                        }
//                        if (cell.getCellType().equals(STRING)) {
//                            objects[index] = cell.getStringCellValue();
//                        }
//                        if (cell.getCellType().equals(BOOLEAN)) {
//                            objects[index] = cell.getBooleanCellValue();
//                        }
//                        if (cell.getCellType().equals(ERROR)) {
//                            objects[index] = cell.getErrorCellValue();
//                        }
//                        index++;
//                    }
//                    list.add(objects);
//                }
//                log.info("导入文件解析成功！");
//                return list;
//            }catch (Exception e){
//                log.info("导入文件解析失败！");
//                e.printStackTrace();
//            }
//            return null;
//        }

//        //测试导入
//        public static void main(String[] args) {
//            try {
//                String fileName = "f:/test.xlsx";
//                List<Object[]> list = importExcel(fileName);
//                for (int i = 0; i < list.size(); i++) {
//                    User user = new User();
//                    user.setId((Integer) list.get(i)[0]);
//                    user.setUsername((String) list.get(i)[1]);
//                    user.setPassword((String) list.get(i)[2]);
//                    user.setEnable((Integer) list.get(i)[3]);
//                    System.out.println(user.toString());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }



    /*
	 *
	 */
    @RequestMapping(value="/exportWareCheckData", method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void exportWareCheckData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String startTime = System.currentTimeMillis()+"";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("盘点商品信息");
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));

        // 设置注释内容
        comment.setString(new HSSFRichTextString("本次数据由批量导出！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("ssss");

//        String[] headers =  {"商品编码","所属业务","库位编码","状态"};
        String[] headers =  {"商品编码","库位编码","状态"};
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++)
        {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        for (int i = 0 ; i < checkDetailExportList.size() ; i++) {
            WarehouseCheckDetail warehouseCheckDetail1 = checkDetailExportList.get(i);
            // 产生表格标题行
            HSSFRow row1 = sheet.createRow(i+1);
            for ( int j=0 ; j < headers1.length; j++)
            {
                HSSFCell cell = row1.createCell(j);
                HSSFRichTextString text = new HSSFRichTextString(headers1[j]);
                cell.setCellValue(text);
            }
        }
        OutputStream output = response.getOutputStream();
        try
        {
            request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
            String fileNameExport=startTime+"-"+System.currentTimeMillis()+".xls";
            String recommendedName = new String(fileNameExport.getBytes(),"iso_8859_1");//防止文件名乱码
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ recommendedName);//下载文件名
            workbook.write(output);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            output.close();
        }
    }

}
