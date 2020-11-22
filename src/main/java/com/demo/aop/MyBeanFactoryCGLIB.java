package com.demo.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactoryCGLIB {

    public static DicServiceImpl createDicService() {
        // 目标类
        final DicServiceImpl dicService = new DicServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        /**
         * 代理:CGLIB字节码增强
         */
        // 核心类
        Enhancer enhancer = new Enhancer();
        // 确定父类
        enhancer.setSuperclass(dicService.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 前置方法执行
                myAspect.before();
                // 目标方法执行
                Object obj = methodProxy.invokeSuper(proxy, args);
                // 后置方法执行
                myAspect.after();
                return obj;
            }
        });
        // 创建代理
        DicServiceImpl proxService = (DicServiceImpl) enhancer.create();
        return proxService;
    }
}
