package com.example.demomongo.dao;//wangDD

import com.example.demomongo.po.g01Student;
import org.springframework.data.mongodb.repository.MongoRepository;

//2022-09-2022/9/8-22:49
public interface StudentMapper extends MongoRepository<g01Student, g01Student> {
//接口MongoRepository泛型为实体类及类型 可以使用Resource注入到类
//接口自带查找插入接口  需要实现
}
