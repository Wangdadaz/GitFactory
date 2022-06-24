package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.ShoppingCart;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.service.impl.ShoppingCartServiceImpl;
import com.wang.hm_takeout.dao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//2022-06-2022/6/13-15:51
@RequestMapping("/shoppingCart")
@RestController
@Slf4j
public class ShoppingCartController {


    @Resource
    private ShoppingCartServiceImpl shoppingCartService;

    @Resource
    private UserServiceImpl userService;


    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){

        String phone = (String) redisTemplate.opsForValue().get("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();


        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,id);

        List<ShoppingCart> list = shoppingCartService.list(shoppingCartLambdaQueryWrapper);

        return R.success(list);
    }



    @PostMapping("/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart){
        String phone = (String) redisTemplate.opsForValue().get("phone");
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);
        Long id = one.getId();
        shoppingCart.setUserId(id);

        shoppingCart.setCreateTime(new Date());

        shoppingCartService.save(shoppingCart);

        System.out.println(shoppingCart);

        return R.success("添加成功");
    }



    @DeleteMapping("/clean")
    public R<String> clean(){


        String phone = (String) redisTemplate.opsForValue().get("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);

        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();

        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();

        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,id);

        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);


        return R.success("已清空");
    }

//    请求 URL: http://localhost:8080/shoppingCart/sub

    @PostMapping("/sub")
    public R<String> sub(ShoppingCart shoppingCart){

        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
        LambdaQueryWrapper<ShoppingCart> CartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        shoppingCartService.remove(CartLambdaQueryWrapper);

        return R.success("删除成功");

    }

}
