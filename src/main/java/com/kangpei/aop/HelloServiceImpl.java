package com.kangpei.aop;

public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello, i am steve");
    }
}
