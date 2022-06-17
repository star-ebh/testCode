package com.example.entity;

/**
 * @author hkh
 * @version 1.0.0
 * @Description ThisTest01
 * @createTime 2022年05月09日 11:10:00
 */
public class ThisTest02 extends ThisTest01{

    @Override
    public void save(){
        System.out.println("ThisTest02-save");
    }

    public  void test(){
//        super.save();
        save();
    }
}
