package com.demo.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAspectImpl implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 前置方法执行
        System.out.println("before");
        // 目标方法执行
        Object obj = methodInvocation.proceed();
        // 后置方法执行
        System.out.println("after");
        return obj;
    }
}
