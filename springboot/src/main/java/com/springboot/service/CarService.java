package com.springboot.service;

import com.springboot.domain.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务逻辑类
 *
 * @author JiangXh
 */
@Service
public class CarService {

    public List<Car> list(){
        Car car1 = new Car(1,"大奔",234234d,new Date());
        Car car2 = new Car(2,"凯迪拉克",888888d,new Date());
        Car car3 = new Car(3,"玛莎拉蒂",999999d,new Date());
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        return carList;
    }
}
