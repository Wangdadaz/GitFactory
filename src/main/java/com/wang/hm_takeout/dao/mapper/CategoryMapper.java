package com.wang.hm_takeout.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.hm_takeout.dao.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86182
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-05-12 22:27:47
* @Entity dao.domain.Category
*/
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}




