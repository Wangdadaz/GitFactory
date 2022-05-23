package com.wang.hm_takeout.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.Setmeal;
import com.wang.hm_takeout.dao.domain.SetmealDish;
import com.wang.hm_takeout.dao.dto.SetmealDto;
import com.wang.hm_takeout.dao.mapper.SetmealMapper;
import com.wang.hm_takeout.dao.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86182
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-05-19 20:41:30
*/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService {

    @Autowired
    private SetmealDishServiceImpl setmealDishService;

    public void saveSetmealDto(SetmealDto setmealDto,Long id) {

        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        List<SetmealDish> collect = setmealDishes.stream().map(setmealDish -> {
            setmealDish.setSetmealId(String.valueOf(setmealDto.getId()));
            setmealDish.setUpdateTime(new Date());
            setmealDish.setCreateTime(new Date());
            setmealDish.setCreateUser(id);
            setmealDish.setUpdateUser(id);
            return setmealDish;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(collect);
    }
}




