package mq.demomongo.po;//wangDD

import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

//2022-09-2022/9/7-22:38
//@Document(collection = "student") 集合名称 如与类名相同可以省略
//@CompoundIndexes()  复合索引
@Component
public class g01Student implements Serializable {
    public g01Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public g01Student(String name, String son, String sex, Integer age, String major, String idcard, String address) {
        this.name = name;
        this.son = son;
        this.sex = sex;
        this.age = age;
        this.major = major;
        this.idcard = idcard;
        this.address = address;
    }

    private String name ;  //名字
    private String son;    //学号
    private String sex;     //性别
    private Integer age;    //年龄
    private String major;   //专业
    private String idcard;  //身份证
    private String address; //地址


}
