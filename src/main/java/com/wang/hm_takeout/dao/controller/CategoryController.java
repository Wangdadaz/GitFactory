package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Category;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//2022-05-2022/5/12-22:43
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;


//    GET
//    http://localhost:8080/category/page?page=1&pageSize=10
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        log.info("获取的page{},获取的size{},获取的name{}",page,pageSize);
        Page<Category> categoryPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.orderByAsc(Category::getSort);
        categoryService.page(categoryPage,categoryLambdaQueryWrapper);
        return R.success(categoryPage);
    }


    @PostMapping
    public R<String> insertEmp(@RequestBody Category category, HttpServletRequest request){
        log.info("获取到的分类信息{}",category);
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        category.setCreateUser(employee.getCreateUser());
        category.setUpdateUser(employee.getCreateUser());
        categoryService.save(category);
        return R.success("增加成功");
    }

    @DeleteMapping
    public R<String> delete(String ids){
        Long id = Long.valueOf(ids);
        log.info("删除的ID",id);
        categoryService.removeById(id);
        System.out.println("执行了分类删除");
        return R.success("删除成功");
    }

}
