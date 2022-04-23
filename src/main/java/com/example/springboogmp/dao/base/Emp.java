package com.example.springboogmp.dao.base;//wangDD

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;

//2022-01-2022/1/30-19:36
@Repository
@ApiModel(value = "员工表实体类")
@Data
public class Emp {

    /**
     * create table emp (
     * id int primary key auto_increment,  #无特殊意义
     * empid int unique not null,  #员工编号
     * empname varchar(10) not null,   #员工姓名
     * emp_password char(32) not null,   #员工账号密码
     * job varchar(10) not null  #工作
     * )
     */
    private Integer id;
    private String empname;
    private String job;
}
