package com.wang.hm_takeout.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.Setmeal;
import com.wang.hm_takeout.dao.domain.SetmealDish;
import com.wang.hm_takeout.dao.dto.SetmealDto;
import com.wang.hm_takeout.dao.mapper.SetmealMapper;
import com.wang.hm_takeout.dao.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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

    @Transactional
    public void removeSermeal(List<Long> ids) {


        try {
            for (int i = 0; i < ids.size(); i++) {
                Setmeal byId = this.getById(ids.get(i));
                File file = new File("D:\\img\\"+byId.getImage());
                file.delete();

            }

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }

    public void saveSetmealDto(SetmealDto setmealDto,Long id) {

        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        List<SetmealDish> collect = setmealDishes.stream().map(setmealDish -> {
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setUpdateTime(new Date());
            setmealDish.setCreateTime(new Date());
            setmealDish.setCreateUser(id);
            setmealDish.setUpdateUser(id);
            return setmealDish;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(collect);
    }

}




