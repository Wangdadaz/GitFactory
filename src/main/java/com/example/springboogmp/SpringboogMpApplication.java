package com.example.springboogmp;

import com.example.springboogmp.dao.base.Menu;
import com.example.springboogmp.dao.interfaceMapper.MenuInterface;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com/example/springboogmp/dao/interfaceMapper")
public class SpringboogMpApplication {
    @Autowired
    private static MenuInterface menu;

    public static void main(String[] args) {
        SpringApplication.run(SpringboogMpApplication.class, args);
        List<Menu> menus = menu.selectList(null);
        menus.forEach(System.out::println);
    }

}
