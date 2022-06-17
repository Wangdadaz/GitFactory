package com.wang.hm_takeout.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.Dish;
import com.wang.hm_takeout.dao.domain.DishFlavor;
import com.wang.hm_takeout.dao.dto.DishDto;
import com.wang.hm_takeout.dao.mapper.DishMapper;
import com.wang.hm_takeout.dao.service.DishFlavorService;
import com.wang.hm_takeout.dao.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86182
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-05-15 16:17:24
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService {


    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional //事务控制
    @Override
    public void insert(DishDto dishDto) {
        this.save(dishDto);

        Long id = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(dish->{
            dish.setDishId(id);
            dish.setUpdateTime(new Date());
            dish.setCreateTime(new Date());
            dish.setCreateUser(id);
            dish.setUpdateUser(id);
            return dish;
        }).collect(Collectors.toList());


        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto selectIDDishDto(Long id) {
        Dish byId = this.getById(id);
        DishDto dishDto = new DishDto();

        BeanUtils.copyProperties(byId,dishDto);

        LambdaUpdateWrapper<DishFlavor> dishFlavorLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishFlavorLambdaUpdateWrapper.eq(DishFlavor::getDishId,id);

        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaUpdateWrapper);

        dishDto.setFlavors(list);


        return dishDto;
    }



    @Value("${reggie.path}")
    String path;
    @Override
    public void removeAndFlavor(Long ids) {

        Dish byId = this.getById(ids);
        String image = byId.getImage();

        File file = new File(path+image);
        file.delete();
        this.removeById(ids);
        LambdaQueryWrapper<DishFlavor> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(DishFlavor::getDishId,ids);
        dishFlavorService.remove(objectLambdaQueryWrapper);
    }
}




