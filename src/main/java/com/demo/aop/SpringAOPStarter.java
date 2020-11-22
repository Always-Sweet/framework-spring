package com.demo.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPStarter {

    public static void main(String[] args) {
        // JDK代理
        DicService dicService = MyBeanFactoryJdk.createDicService();
        dicService.addDic();
        dicService.updateDic();
        dicService.deleteDic();

        // CGLIB字节码增强
        DicServiceImpl dicServiceImpl = MyBeanFactoryCGLIB.createDicService();
        dicServiceImpl.addDic();
        dicServiceImpl.updateDic();
        dicServiceImpl.deleteDic();

        // 半自动配置代理
        String xmlPath = "applicationContextSemiAutomaticAop.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        DicService semiAutoProxService = (DicService) applicationContext.getBean("proxService");
        semiAutoProxService.addDic();
        semiAutoProxService.updateDic();
        semiAutoProxService.deleteDic();

        // 全自动配置代理
        String xmlPath2 = "applicationContextFullyAutomatedAop.xml";
        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext(xmlPath2);
        DicService fullyAutoProxService = (DicService) applicationContext2.getBean("dicService");
        fullyAutoProxService.addDic();
        fullyAutoProxService.updateDic();
        fullyAutoProxService.deleteDic();
    }
}
