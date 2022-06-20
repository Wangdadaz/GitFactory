package com.wang.hm_takeout.dao.controller;//wangDD

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.AddressBook;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.service.impl.AddressBookServiceImpl;
import com.wang.hm_takeout.dao.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

//2022-06-2022/6/13-15:56
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {


    @Resource
    private UserServiceImpl userService;

    @Resource
    private AddressBookServiceImpl addressBookService;


    @PostMapping
    private R<String> add(@RequestBody AddressBook addressBook, HttpSession session){
        String phone = (String)session.getAttribute("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();
        addressBook.setCreateTime(new Date());
        addressBook.setUpdateTime(new Date());
        addressBook.setUserId(id);
        addressBook.setCreateUser(id);
        addressBook.setUpdateUser(id);
        addressBookService.save(addressBook);
        return R.success("添加成功");
    }


//    请求 URL: http://localhost:8080/addressBook/list

    @GetMapping("/list")
    public R<List<AddressBook>> list(HttpSession session){

        String phone = (String)session.getAttribute("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(userLambdaQueryWrapper);

        Long id = one.getId();


        LambdaQueryWrapper<AddressBook> LambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper.eq(AddressBook::getUserId,id);

        List<AddressBook> list = addressBookService.list(LambdaQueryWrapper);


        return R.success(list);
    }


//    请求 URL: http://localhost:8080/addressBook/default

    @PutMapping("/default")
    public R<String> setDefault(@RequestBody AddressBook addressBook){
        Long id = addressBook.getId();


        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault,1);

        if (addressBookService.getOne(addressBookLambdaQueryWrapper)!=null){

            LambdaUpdateWrapper<AddressBook> addressBookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            addressBookLambdaUpdateWrapper.set(AddressBook::getIsDefault,0);

            addressBookService.update(addressBookLambdaUpdateWrapper);



        }
        LambdaUpdateWrapper<AddressBook> LambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        LambdaUpdateWrapper.eq(AddressBook::getId,id)
                .set(AddressBook::getIsDefault,1);

        addressBookService.update(LambdaUpdateWrapper);

        return R.success("修改成功");

    }


    @GetMapping("/default")
    public R<AddressBook> getDefault(HttpSession session){


        String phone = (String)session.getAttribute("phone");

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User one1 = userService.getOne(userLambdaQueryWrapper);

        Long id = one1.getId();


        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault,1).eq(AddressBook::getUserId,id);;

        AddressBook one = addressBookService.getOne(addressBookLambdaQueryWrapper);
        return R.success(one);

    }





}
