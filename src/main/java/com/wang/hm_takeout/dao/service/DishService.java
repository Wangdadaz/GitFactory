package com.wang.hm_takeout.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.hm_takeout.dao.domain.Dish;
import com.wang.hm_takeout.dao.dto.DishDto;

/**
* @author 86182
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-05-15 16:17:24
*/
public interface DishService extends IService<Dish> {


    public void insert(DishDto dishDto);

}
