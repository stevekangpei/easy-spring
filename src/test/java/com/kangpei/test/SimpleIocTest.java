package com.kangpei.test;

import com.kangpei.ioc.ApplicationContext;
import com.kangpei.ioc.Car;
import org.junit.Test;

public class SimpleIocTest {

    @Test
    public void  test() {

        try {
            ApplicationContext context = new ApplicationContext("src/main/resources/Ioc.xml");
            Car car =   (Car) context.getBean("car");
            System.out.println(car);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
