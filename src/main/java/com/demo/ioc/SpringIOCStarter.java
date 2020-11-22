package com.demo.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIOCStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DicService dicService = (DicService) applicationContext.getBean("dicService");
        dicService.printDic();
        dicService.getDicDao().print();
    }
}
