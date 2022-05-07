package com.wang.hm_takeout.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.hm_takeout.dao.domain.Employee;
import org.springframework.stereotype.Repository;

/**
* @author 86182
* @description 针对表【employee(员工信息)】的数据库操作Service
* @createDate 2022-05-07 15:19:05
*/
@Repository
public interface EmployeeService extends IService<Employee> {

}
