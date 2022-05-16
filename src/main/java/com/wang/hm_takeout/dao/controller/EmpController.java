package com.wang.hm_takeout.dao.controller;//wangDD


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.hm_takeout.dao.common.R;
import com.wang.hm_takeout.dao.domain.Employee;
import com.wang.hm_takeout.dao.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

        request.getSession().setAttribute("employee",one);

        return R.success(one);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("employee");

        return R.success("退出成功");
    }


    @PostMapping
    public R<String> insertEmp(@RequestBody Employee emp,HttpServletRequest request){
//        @RequestBody Employee emp
//        将前端返回的josn数据封装为employee对象，需保证返回的key值与employee中的属性值相同
//        name: "wang", phone: "15809950712", sex: "1", idNumber: "111111111111111111", username: "545644
            emp.setCreateTime(new Date());
            emp.setUpdateTime(new Date());
            emp.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        Employee employee1 = (Employee) request.getSession().getAttribute("employee");
        emp.setCreateUser(employee1.getId());
        emp.setUpdateUser(employee1.getId());
        emp.setId(null);
        employee.save(emp);
        return R.success("增加成功");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){

        log.info("获取的page{},获取的size{},获取的name{}",page,pageSize,name);

        Page<Employee> employeePage = new Page(page,pageSize);
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        employee.page(employeePage,employeeLambdaQueryWrapper);


        return R.success(employeePage);
    }

    @PutMapping
    public R<String> update(@RequestBody Employee emp,HttpServletRequest request){
//        {id: 3, status: 0} 响应的是一个josn对象 使用emp封装
        Employee employee1 = (Employee) request.getSession().getAttribute("employee");
        log.info("修改前的emp对象{}",emp);
        emp.setUpdateTime(new Date());
        emp.setUpdateUser(employee1.getId());
        boolean b = employee.updateById(emp);
        return R.success(b ? "修改成功":"修改失败");
    }

    @GetMapping("/{id}")
    public R<Employee> selectID(@PathVariable Long id){
        log.info("获取到ID:{}",id);
        Employee byId = employee.getById(id);
        return R.success(byId);

    }



}
