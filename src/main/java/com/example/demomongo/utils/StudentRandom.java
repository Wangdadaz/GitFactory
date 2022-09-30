package com.example.demomongo.utils;//wangDD

import com.example.demomongo.po.g01Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

//2022-09-2022/9/30-13:52
@Component
public class StudentRandom {


    public g01Student create(){
        String[] strings = {"大数据专业","计算机专业"};
        g01Student g01Student = new g01Student();
        String chinese = ChineseName.getChinese();
        g01Student.setName(chinese);
        long l = System.currentTimeMillis();
        g01Student.setSon(String.valueOf(l));
        Random random = new Random();
        int i = random.nextInt(40);
        String sex = "男";
        String magor = strings[0];
        if (i%2==0){
            sex="女";
            magor=strings[1];
        }
        g01Student.setSex(sex);
        g01Student.setAge(i+10);
        g01Student.setMajor(magor);
        g01Student.setIdcard(CertNoUtil.getRandomID());
        g01Student.setAddress(Address.getProCity());

        return g01Student;
    }

}
