package com.wang.hm_takeout.dao.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.Category;
import com.wang.hm_takeout.dao.mapper.CategoryMapper;
import com.wang.hm_takeout.dao.service.CategoryService;
import org.springframework.stereotype.Service;
/**
* @author 86182
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-05-12 22:27:47
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}




