package com.example.springboogmp;

import com.example.springboogmp.dao.base.Emp;
import com.example.springboogmp.dao.base.Menu;
import com.example.springboogmp.dao.interfaceMapper.EmpInterface;
import com.example.springboogmp.dao.interfaceMapper.MenuInterface;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com/example/springboogmp/dao/interfaceMapper")
class SpringboogMpApplicationTests {
    @Autowired
    private MenuInterface menu;

    @Autowired
    private EmpInterface emp;

    @Test
    void contextLoads() {
        SpringApplication.run(SpringboogMpApplication.class);
        List<Menu> menus = menu.selectList(null);
        menus.forEach(System.out::println);
    }

    @Test
    void  emp(){
        SpringApplication.run(SpringboogMpApplication.class);
        List<Emp> emps = emp.selectList(null);
        emps.forEach(System.out::println);

    }

}
