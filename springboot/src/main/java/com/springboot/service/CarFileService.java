package com.springboot.service;

import com.springboot.domain.Car;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarFileService {

    public String createExcel(){
        Car car1 = new Car(1,"大奔",234234d,new Date());
        Car car2 = new Car(2,"凯迪拉克",888888.34d,new Date());
        Car car3 = new Car(3,"玛莎拉蒂",999999d,new Date());
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("小汽车");
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        // 设置字体
        HSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short)10);

        // 设置单元格样式
        style1.setFont(font1); // 注入字体
        style1.setWrapText(true); // 自动换行
        style1.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        //style1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // 单元格背景色
        //style1.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 背景色填充方式
        style1.setBorderTop(BorderStyle.THIN); // 设置上边框样式
        style1.setBorderBottom(BorderStyle.THIN); // 设置下边框样式

        HSSFDataFormat dataFormat = wb.createDataFormat();
        //style1.setDataFormat(dataFormat.getFormat("0.00"));
        //style1.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));

        sheet.setColumnWidth(0,20*256);  // 设置列宽,0 为第一列
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,20*256);


        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)800);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(style1);

        cell = row.createCell(1);
        cell.setCellValue("name");
        cell.setCellStyle(style1);

        cell = row.createCell(2);
        cell.setCellValue("price");
        cell.setCellStyle(style1);

        cell = row.createCell(3);
        cell.setCellValue("createDate");
        cell.setCellStyle(style1);

        for(int i=1;i<=carList.size();i++){
            row = sheet.createRow(i);
            row.setHeight((short)800);
            cell = row.createCell(0);
            cell.setCellValue(carList.get(i-1).getId());
            cell.setCellStyle(style1);

            cell = row.createCell(1);
            cell.setCellValue(carList.get(i-1).getName());
            cell.setCellStyle(style1);

            cell = row.createCell(2);
            cell.setCellValue(carList.get(i-1).getPrice());
            cell.setCellStyle(style1);

            cell = row.createCell(3);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(carList.get(i-1).getCreateDate());
            cell.setCellValue(date);
            cell.setCellStyle(style1);
        }
        long times = new Date().getTime();
        String path = "carfile";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String finalPath = file.getName()+"/"+times+".xls";
        try {
            FileOutputStream os = new FileOutputStream(finalPath);
            wb.write(os);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalPath;
    }

    public static void main(String[] args) {
        String filename = new CarFileService().createExcel();
        System.out.println(filename);
    }

}
