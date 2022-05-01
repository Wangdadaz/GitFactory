package com.example.springboogmp.lambda;//wangDD

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

//2022-04-2022/4/28-22:31
public class Test01 {
    public static void main(String[] args) { //[方法参数] -> {[方法体]}
//        new Thread(() ->{
//            System.out.println("匿名内部类+线程");
//        }).start();
        int te = te((int a, int b) -> {
            return a + b;
        });
        System.out.println(te);

        int te1 = te((a, b) -> a + b);
        System.out.println(te1);
        String s = " ";
        boolean notBlank = StringUtils.isNotBlank(s);
        System.out.println(notBlank);


    }
    static int te(Count c){
        int a = 10;
        int b = 20;
        return c.sum(a,b);
    }
}
