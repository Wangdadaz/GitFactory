package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Orders;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.service.impl.OrdersServiceImpl;
import com.wang.hm_takeout.dao.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//2022-06-2022/6/13-20:46
@RestController
@RequestMapping("/order")
public class OrdesController {


    @Resource
    private UserServiceImpl userService;

    @Resource
    private OrdersServiceImpl ordersService;

//    请求 URL: http://localhost:8080/order/userPage?page=1&pageSize=1

    @GetMapping("/userPage")
    public R<Page> page(int page, int pageSize, HttpSession session){

        String phone = (String)session.getAttribute("phone");
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();

        Page<Orders> ordersPage = new Page<>(page,pageSize);

        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();

        ordersLambdaQueryWrapper.eq(Orders::getUserId,id);

        Page<Orders> page1 = ordersService.page(ordersPage,ordersLambdaQueryWrapper);


        return R.success(page1);
    }



//    请求 URL: http://localhost:8080/order/submit

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders, HttpSession session){
//        {remark: "", payMethod: 1, addressBookId: "1537434928755539970"}
        String phone = (String)session.getAttribute("phone");
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();



        ordersService.sub(orders,one);

        return R.success("下单成功！");


    }

}
