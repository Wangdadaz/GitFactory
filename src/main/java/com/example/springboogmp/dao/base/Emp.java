package com.example.springboogmp.dao.base;//wangDD

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

//2022-01-2022/1/30-19:36
@Repository
@ApiModel(value = "员工表实体类")
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
    @ApiModelProperty(value = "索引ID")
    private Integer id;
    @ApiModelProperty("员工ID")
    private String empid;
    @ApiModelProperty("员工姓名")
    private String empname;
    @ApiModelProperty("密码")
    private String emp_password;
    @ApiModelProperty("员工岗位")
    private String job;

    public Emp() {

    }

    public Emp(Integer id, String empid, String empname, String password, String job) {
        this.id = id;
        this.empid = empid;
        this.empname = empname;
        this.emp_password = password;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", empid='" + empid + '\'' +
                ", empname='" + empname + '\'' +
                ", password='" + emp_password + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getPassword() {
        return emp_password;
    }

    public void setEmp_password(String password) {
        this.emp_password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
