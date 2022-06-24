package com.wang.hm_takeout.dao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public R<User> login(@RequestBody User user){


        String phone = user.getPhone();

        redisTemplate.opsForValue().set("phone",phone,2, TimeUnit.HOURS);
//        session.setAttribute("phone",phone);
        if (StringUtils.isNotEmpty(phone)){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone,phone);
            User one = userService.getOne(userLambdaQueryWrapper);
            if (one==null){
                userService.save(user);

            }
            return R.success(user);
        }


        return R.error("登录失败！！！");
    }


//    请求 URL: http://localhost:8080/user/loginout

    @PostMapping("/loginout")
    public R<String> loginout(){
        redisTemplate.delete("phone");
        return R.success("已退出");

    }

}
