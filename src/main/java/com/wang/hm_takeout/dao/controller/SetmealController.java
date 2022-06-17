package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Category;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.domain.Setmeal;
import com.wang.hm_takeout.dao.dto.SetmealDto;
import com.wang.hm_takeout.dao.service.impl.CategoryServiceImpl;
import com.wang.hm_takeout.dao.service.impl.DishServiceImpl;
import com.wang.hm_takeout.dao.service.impl.SetmealDishServiceImpl;
import com.wang.hm_takeout.dao.service.impl.SetmealServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//2022-05-2022/5/19-20:47
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealServiceImpl setmealService;

    @Autowired
    private SetmealDishServiceImpl setmealDishService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private DishServiceImpl dishService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("获取的page{},获取的size{},获取的name{}",page,pageSize,name);
        Page<Setmeal> setmealPage = new Page<>(page,pageSize);
        Page<SetmealDto> setmealDishPage = new Page<>(page,pageSize);
         BeanUtils.copyProperties(setmealPage,setmealDishPage,"records");
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        setmealService.page(setmealPage,setmealLambdaQueryWrapper);
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> collect = records.stream().map(setmeal -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(setmeal, setmealDto);
            Long categoryId = setmeal.getCategoryId();
            if (categoryId != null) {
                Category byId = categoryService.getById(categoryId);
                setmealDto.setCategoryName(byId.getName());
            }
            return setmealDto;

        }).collect(Collectors.toList());
        setmealDishPage.setRecords(collect);

        return R.success(setmealDishPage);
    }


    @GetMapping("/dish/{id}")
    public R<Setmeal> getDish(@PathVariable Long id){

        Setmeal byId = setmealService.getById(id);

        return R.success(byId);
    }

    //    http://localhost:8080/setmeal/status/0?ids=1415580119015145474
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable int status, Long ids, HttpServletRequest request){
        log.info("获取的status{}，IDS{}",status,ids);

        Employee employee = (Employee)request.getSession().getAttribute("employee");

        LambdaUpdateWrapper<Setmeal> setmealLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        setmealLambdaUpdateWrapper.set(Setmeal::getStatus,status)
                .set(Setmeal::getUpdateTime,new Date())
                .set(Setmeal::getUpdateUser,employee.getId())
                .eq(Setmeal::getId,ids);

        setmealService.update(setmealLambdaUpdateWrapper);

        return R.success("修改成功");
    }



//    http://localhost:8080/setmeal 添加套餐的请求路径
    @PostMapping
    public R<String> addSetmeal(@RequestBody SetmealDto setmealDto,HttpServletRequest request){

        Employee employee = (Employee)request.getSession().getAttribute("employee");

        setmealDto.setCreateTime(new Date());  //设置创建时间
        setmealDto.setUpdateTime(new Date());  //设置修改时间
        setmealDto.setCreateUser(employee.getCreateUser());        //设置创建用户
        setmealDto.setUpdateUser(employee.getCreateUser());        //设置修改用户
        setmealService.saveSetmealDto(setmealDto,employee.getId());


        return R.success("添加成功！");
    }
    //    http://localhost:8080/setmeal?ids=1528653298385604610  //删除请求
    @DeleteMapping
    public R<String> deleteSetmeal(@RequestParam List<Long> ids){

        setmealService.removeSermeal(ids);

        return R.success("删除成功");
    }


    //    请求 URL: http://localhost:8080/setmeal/list?categoryId=1413342269393674242&status=1


    @RequestMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){


        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,setmeal.getCategoryId())
                .eq(Setmeal::getStatus,setmeal.getStatus());
        List<Setmeal> list = setmealService.list(setmealLambdaQueryWrapper);

        return R.success(list);
    }


    //    请求 URL: http://localhost:8080/setmeal/list?categoryId=1413342269393674242&status=1
}
