package com.example.springboogmp.mybatisTest;//wangDD

import com.example.springboogmp.dao.base.Menu;
import com.example.springboogmp.dao.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;

//2022-04-2022/4/26-10:50
@SpringBootTest
public class ServiceTest {
    @Autowired
    private MenuServiceImpl menu;

    @Test
    void test(){
        long count = menu.count();
        System.out.println(count);
//        查询总记录数
    }

    @Test
    void inserts(){

        ArrayList<Menu> menus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Menu menu = new Menu();
            menu.setMoney(1.0+i);
            menu.setM_type("测试");
            menu.setM_name("测试"+i);
            menus.add(menu);
        }
        boolean b = menu.saveBatch(menus);
        System.out.println(b);
    }

    @Test
    void delete(){
        boolean b = menu.removeById(99);
        System.out.println(b);


    }


}
