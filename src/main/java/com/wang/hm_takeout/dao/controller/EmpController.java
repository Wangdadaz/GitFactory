package com.wang.hm_takeout.dao.controller;//wangDD


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

//2022-05-2022/5/6-21:44
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmpController {

    @Autowired
    private EmployeeServiceImpl employee;


    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee emp){
//        @RequestBody  前端页面返回的josn数据 使用emp对象去接收 josn的key和字段名需一致
//        return {
//        'username': [{ 'validator': validateUsername, 'trigger': 'blur' }],
//        'password': [{ 'validator': validatePassword, 'trigger': 'blur' }]
//          }
        String password = emp.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        Employee one = employee.getOne(new LambdaQueryWrapper<Employee>().eq(Employee::getUsername, emp.getUsername()));

        if (one==null){
            return R.error("用户不存在");
        }

        if(!one.getPassword().equals(password)){
            return R.error("密码错误");
        }

        if (one.getStatus()!=1){
            return R.error("用户不可用");
        }

        request.getSession().setAttribute("employee",emp);

        return R.success(one);
    }

}
