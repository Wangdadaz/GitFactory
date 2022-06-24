package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Category;
import com.wang.hm_takeout.dao.domain.Dish;
import com.wang.hm_takeout.dao.domain.DishFlavor;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.dto.DishDto;
import com.wang.hm_takeout.dao.service.impl.CategoryServiceImpl;
import com.wang.hm_takeout.dao.service.impl.DishFlavorServiceImpl;
import com.wang.hm_takeout.dao.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//2022-05-2022/5/15-16:21
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private DishServiceImpl dishService;

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;


    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("获取到的菜品分页数据page{},pageSize{},name{}",page,pageSize,name);
        Page<Dish> dishPage = new Page<>(page,pageSize);  //创建分页对象
        Page<DishDto> dishDtoPage = new Page<>(page,pageSize); //使用dto复制dish对象

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        dishService.page(dishPage);

//        需要一个dishdto对象作为返回值

        BeanUtils.copyProperties(dishPage,dishDtoPage,"records"); //拷贝dish到dishdto中，忽略flavors

        List<Dish> records = dishPage.getRecords();   //获取records为下面的dto赋值

        List<DishDto> collect = records.stream().map(dish -> {
            DishDto dishDto = new DishDto();  //创建dishdto对象
            BeanUtils.copyProperties(dish, dishDto);  //将dish复制到dishdto中
            Long categoryId = dishDto.getCategoryId();//获取分类的ID
            Category byId = categoryService.getById(categoryId);//通过ID查询分类对象
            if (byId!=null){//判断查询结果
                String name1 = byId.getName();//分类名字
                dishDto.setCategoryName(name1);//将分类名赋值给dishdto
            }

            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(collect);

        return R.success(dishDtoPage);
    }


    @PostMapping
    public R<String> insert(@RequestBody DishDto dishDto, HttpServletRequest request){
        //菜品添加

        String key = "dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();

        redisTemplate.delete(key);

        Employee employee = (Employee) request.getSession().getAttribute("employee");//获取当前登录的用户信息
        dishDto.setCreateTime(new Date());  //设置创建时间
        dishDto.setUpdateTime(new Date());  //设置修改时间
        dishDto.setCreateUser(employee.getCreateUser());        //设置创建用户
        dishDto.setUpdateUser(employee.getCreateUser());        //设置修改用户

        dishService.insert(dishDto);

        return R.success("添加成功");
    }


    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable int status,Long ids, HttpServletRequest request){
//        http://localhost:8080/dish/status/
        Employee employee = (Employee) request.getSession().getAttribute("employee");

        LambdaUpdateWrapper<Dish> dishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishLambdaUpdateWrapper.set(Dish::getStatus,status).set(Dish::getUpdateTime,new Date())
                .set(Dish::getUpdateUser,employee.getId()).eq(Dish::getId,ids);

        dishService.update(dishLambdaUpdateWrapper);
        return R.success("修改成功!");
    }


    @DeleteMapping
    public R<String> delete(Long ids){


        log.info("删除的ID",ids);
        dishService.removeAndFlavor(ids);

        System.out.println("执行了分类删除");
        return R.success("删除成功");
    }

    @GetMapping("/{id}")
    public R<DishDto> getDish(@PathVariable Long id){
        DishDto dishDto = dishService.selectIDDishDto(id);
        System.out.println(dishDto.toString());
        return R.success(dishDto);
    }


    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        List<DishDto> dishDtoList = null;

        String key = "dish_"+dish.getCategoryId()+"_"+dish.getStatus();

        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);

        //判断缓存是否存在

        if (dishDtoList!=null){
            return R.success(dishDtoList);

        }else {
            //在菜品管理的菜品分类下拉选项查询
            log.info("获取到的参数：{}",dish.getCategoryId());
            LambdaQueryWrapper<Dish> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            categoryLambdaQueryWrapper.eq(Dish::getCategoryId,dish.getCategoryId());
            List<Dish> list = dishService.list(categoryLambdaQueryWrapper);


            dishDtoList = list.stream().map(dishs -> {
                DishDto dishDto = new DishDto();  //创建dishdto对象
                BeanUtils.copyProperties(dishs, dishDto);  //将dish复制到dishdto中
                Long categoryId = dishDto.getCategoryId();//获取分类的ID
                Category byId = categoryService.getById(categoryId);//通过ID查询分类对象
                if (byId!=null){//判断查询结果
                    String name1 = byId.getName();//分类名字
                    dishDto.setCategoryName(name1);//将分类名赋值给dishdto
                }
                Long id = dishs.getId();
                LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
                dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,id);
                List<DishFlavor> list1 = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
                dishDto.setFlavors(list1);
                return dishDto;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(key,dishDtoList,3, TimeUnit.MINUTES);
        }
        return R.success(dishDtoList);
    }
}
