package com.springboot.controller;

import com.springboot.pojo.Car;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Car控制器
 *
 * @author JiangXh
 */
@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<?> list(){
        List<Car> list = carService.list();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
