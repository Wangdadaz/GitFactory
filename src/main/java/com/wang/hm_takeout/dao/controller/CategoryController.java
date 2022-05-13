package com.wang.hm_takeout.dao.controller;//wangDD

import com.wang.hm_takeout.dao.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//2022-05-2022/5/12-22:43
@Slf4j
@RestController
@RequestMapping("/controller")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl category;





}
