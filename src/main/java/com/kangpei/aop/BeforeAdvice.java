package com.kangpei.aop;

import java.lang.reflect.Method;

public class BeforeAdvice implements Advice {

    private Object bean;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        methodInvocation.invoke();
        Object object = method.invoke(bean, args);
        return object;
    }
}
