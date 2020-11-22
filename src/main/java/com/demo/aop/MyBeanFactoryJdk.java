package com.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactoryJdk {

    public static DicService createDicService() {
        // 目标类
        final DicService dicService = new DicServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        /**
         * 代理:Jdk动态代理
         */
        DicService proxService = (DicService) Proxy.newProxyInstance(MyBeanFactoryJdk.class.getClassLoader(), dicService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 前置方法执行
                myAspect.before();
                // 目标方法执行
                Object obj = method.invoke(dicService, args);
                // 后置方法执行
                myAspect.after();
                return obj;
            }
        });

        return proxService;
    }
}
