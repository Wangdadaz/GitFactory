package com.wang.hm_takeout.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.User;
import com.wang.hm_takeout.dao.mapper.UserMapper;
import com.wang.hm_takeout.dao.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 86182
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-06-12 17:21:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




