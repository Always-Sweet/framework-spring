package com.demo.ioc;

import org.springframework.stereotype.Component;

@Component
public class DicService {

    DicDao dicDao;
    String name;

    public DicService() {
    }

    public DicService(String name) {
        this.name = name;
    }

    public void printDic() {
        System.out.println("service go go");
    }

    public DicDao getDicDao() {
        return dicDao;
    }

    public void setDicDao(DicDao dicDao) {
        this.dicDao = dicDao;
    }
}
