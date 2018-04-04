package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.Date;
import java.util.List;

/**
 * Car控制器
 *
 * @author JiangXh
 */
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "carlist",method = RequestMethod.GET)
    public ModelAndView list(){
        List<Car> cars = carService.list();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("carlist");
        mv.addObject("cars",cars);

        Car car = new Car(4,"BMW",234234d,new Date());
        mv.addObject("car",car);
        return mv;
    }

    @RequestMapping("hiThymeleaf")
    public String hiThymeleaf(
            @RequestParam(value = "name",required = false,defaultValue = "world") String name,
            Model model
    ){
        // 模型数据
        model.addAttribute("name",name);
        // 视图（resources下的模板中的html：hello.html）
        return "hello";
    }

}
