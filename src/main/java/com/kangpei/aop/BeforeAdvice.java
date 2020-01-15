package com.kangpei.aop;

import java.lang.reflect.Method;
import java.util.zip.Adler32;

public class BeforeAdvice implements Advice {

    private Object bean;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        methodInvocation.invoke();  // 先调用
        Object object = method.invoke(bean, args);
        return object;
    }
}
