package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Dish;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.dto.DishDto;
import com.wang.hm_takeout.dao.service.impl.DishFlavorServiceImpl;
import com.wang.hm_takeout.dao.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//2022-05-2022/5/15-16:21
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {


    @Autowired
    private DishServiceImpl dishService;

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;


    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("获取到的菜品分页数据page{},pageSize{},name{}",page,pageSize,name);
        Page<Dish> dishPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        dishService.page(dishPage);
        return R.success(dishPage);
    }


    @PostMapping
    public R<String> insert(@RequestBody DishDto dishDto, HttpServletRequest request){


        Employee employee = (Employee) request.getSession().getAttribute("employee");
        dishDto.setCreateTime(new Date());
        dishDto.setUpdateTime(new Date());
        dishDto.setCreateUser(employee.getCreateUser());
        dishDto.setUpdateUser(employee.getCreateUser());

        dishService.insert(dishDto);

        return R.success("添加成功");
    }


}
