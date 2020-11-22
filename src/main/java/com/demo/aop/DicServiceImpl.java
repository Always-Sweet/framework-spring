package com.demo.aop;

public class DicServiceImpl implements DicService {

    @Override
    public void addDic() {
        System.out.println("addDic……");
    }

    @Override
    public void updateDic() {
        System.out.println("updateDic……");
    }

    @Override
    public void deleteDic() {
        System.out.println("deleteDic……");
    }
}
