package com.example.springboogmp.mybatisTest;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springboogmp.dao.base.Emp;
import com.example.springboogmp.dao.base.Menu;
import com.example.springboogmp.dao.interfaceMapper.EmpInterface;
import com.example.springboogmp.dao.service.impl.EmpServiceImpl;
import com.example.springboogmp.dao.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//2022-04-2022/4/26-10:50
@SpringBootTest
public class ServiceTest {
    @Autowired
    private MenuServiceImpl menu;

    @Autowired
    private EmpServiceImpl emp;

    @Autowired
    private EmpInterface empi;
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
        boolean b = menu.removeById(1025);
        System.out.println(b);
    }

    @Test
    void deleteWhere(){
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.gt("id",20);
        boolean remove = menu.remove(menuQueryWrapper);
        System.out.println(remove);
        //queryWrapper
    }

    @Test
    void selectWhere(){

        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.ge("id",10);
        List<Menu> menus = menu.getBaseMapper().selectList(menuQueryWrapper);
        menus.forEach(System.out::println);
    }

    @Test
    void selectWhere2(){

        List<Map<String, Object>> maps = empi.selectMaps(new QueryWrapper<Emp>().select("empid", "empname", "job").eq("job","老板"));

        maps.forEach(System.out::println);
    }

    @Test
    void selectWhere3(){

        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.inSql("id","select id from menu where id<7");
        List<Menu> list = menu.list(menuQueryWrapper);
        list.forEach(System.out::println);


    }

    @Test
    void selectWhere4(){

        UpdateWrapper<Menu> menuUpdateWrapper = new UpdateWrapper<>();
        menuUpdateWrapper.eq("id",6).set("money",12.0);

        boolean update = menu.update(menuUpdateWrapper);
        System.out.println(update);
    }

    @Test
    void lambda(){
        LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();

        menuLambdaQueryWrapper.eq(Menu::getId,1);
        Menu one = menu.getOne(menuLambdaQueryWrapper);
        System.out.println(one);
    }


    @Test
    void lambda2(){

        LambdaQueryWrapper<Emp> empLambdaQueryWrapper = new LambdaQueryWrapper<>();

        empLambdaQueryWrapper.ge(Emp::getId,3).eq(Emp::getJob,"特级厨师").select(Emp::getEmpname,Emp::getJob);

        List<Emp> list = emp.list(empLambdaQueryWrapper);

        list.forEach(System.out::println);

    }


    @Test
    void lambda3(){

        LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.like(Menu::getM_name,"麻").and(i -> i.gt(Menu::getId,3)).select(Menu::getM_name,Menu::getM_type,Menu::getMoney);
        List<Menu> list = menu.list(menuLambdaQueryWrapper);
        list.forEach(System.out::println);
    }



}
