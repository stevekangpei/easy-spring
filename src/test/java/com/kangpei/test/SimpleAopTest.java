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
        MethodInvocation logTask1 =  () -> {System.out.println("logTask ends");};

        Advice advice = new BeforeAdvice(helloService, logTask);
        Advice afterAdvice = new AfterAdvice(helloService, logTask1);

        HelloService helloServiceProxy = (HelloService) SimpleAop.getProxy(helloService, advice);
        HelloService helloServiceProxy2 = (HelloService) SimpleAop.getProxy(helloService, afterAdvice);

        helloServiceProxy.sayHello();

        helloServiceProxy2.sayHello();
    }
}
