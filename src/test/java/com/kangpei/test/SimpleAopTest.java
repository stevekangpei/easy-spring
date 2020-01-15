package com.kangpei.test;

import com.kangpei.aop.*;
import org.junit.Test;

public class SimpleAopTest {


    @Test
    public void test() {

        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();
        System.out.println("After aop.....");
        MethodInvocation logTask =  () -> {System.out.println("logTask begins");};

        Advice advice = new BeforeAdvice(helloService, logTask);
        HelloService helloServiceProxy = (HelloService) SimpleAop.getProxy(helloService, advice);

        helloServiceProxy.sayHello();
    }
}
