package com.wang.hm_takeout.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.hm_takeout.dao.domain.Category;
import org.springframework.stereotype.Repository;

/**
* @author 86182
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-05-12 22:27:47
*/
@Repository
public interface CategoryService extends IService<Category> {

}
