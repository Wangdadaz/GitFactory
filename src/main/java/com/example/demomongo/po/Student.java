package com.example.demomongo.po;//wangDD

import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//2022-09-2022/9/7-22:38
//@Document(collection = "student") 集合名称 如与类名相同可以省略
//@CompoundIndexes()  复合索引
public class Student implements Serializable {

    private Integer son;
    private String name ;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "son=" + son +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setSon(Integer son) {
        this.son = son;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSon() {
        return son;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
