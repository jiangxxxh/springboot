package com.springboot.controller;

import com.springboot.service.CarFileService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.UnicodeEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {

    @Autowired
    private CarFileService carFileService;

    @RequestMapping("hellospringboot")
    public String hello(){
        return "hello spring boot";
    }

    @RequestMapping("downloadfile")
    public ResponseEntity<?> getFile(){
        String filename = carFileService.createExcel(); // 获得文件的相对路径
        File srcFile = new File(filename); // 生成文件
        String displayName = "小汽车"+filename.substring(filename.lastIndexOf(".")); // 给用户的下载名字
        byte[] buf = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            buf = new byte[(int)srcFile.length()];
            fis.read(buf); // 输入流到数组里
            fis.close();
            headers.add("Content-Disposition",
                    "attachment;filename="+URLEncoder.encode(displayName,"utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(buf,headers,HttpStatus.OK);
    }


}
