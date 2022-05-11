package com.wang.hm_takeout.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.hm_takeout.dao.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86182
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-05-06 21:33:18
* @Entity dao.domain.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {


}




