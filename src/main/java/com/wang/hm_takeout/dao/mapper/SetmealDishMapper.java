package com.wang.hm_takeout.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.hm_takeout.dao.domain.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86182
* @description 针对表【setmeal_dish(套餐菜品关系)】的数据库操作Mapper
* @createDate 2022-05-20 22:23:43
* @Entity dao.domain.SetmealDish
*/
@Repository
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

}




