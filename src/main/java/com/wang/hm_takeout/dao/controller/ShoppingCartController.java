package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.ShoppingCart;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.service.impl.ShoppingCartServiceImpl;
import com.wang.hm_takeout.dao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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


    @GetMapping("/list")
    public R<List<ShoppingCart>> list(HttpSession session){

        String phone = (String)session.getAttribute("phone");

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
    public R<String> add(@RequestBody ShoppingCart shoppingCart, HttpSession session){
        String phone = (String) session.getAttribute("phone");
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
    public R<String> sub(HttpSession session){


        String phone = (String)session.getAttribute("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);

        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();

        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();

        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,id);

        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);


        return R.success("已清空");
    }

}
