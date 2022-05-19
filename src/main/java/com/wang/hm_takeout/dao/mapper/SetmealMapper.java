package com.wang.hm_takeout.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.hm_takeout.dao.domain.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86182
* @description 针对表【setmeal(套餐)】的数据库操作Mapper
* @createDate 2022-05-19 20:41:30
* @Entity dao.domain.Setmeal
*/
@Mapper
@Repository
public interface SetmealMapper extends BaseMapper<Setmeal> {

}




