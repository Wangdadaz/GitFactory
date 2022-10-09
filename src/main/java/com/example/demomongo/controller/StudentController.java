package com.example.demomongo.controller;//wangDD

import com.example.demomongo.po.g01Student;
import com.example.demomongo.service.StudentService;
import com.example.demomongo.utils.StudentRandom;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//2022-09-2022/9/8-23:19
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/all")
    public List<g01Student> getAll(){
        return studentService.listStudent();
    }

    @GetMapping("/delete")
    public String deleteAll(){

        studentService.delete();
        return "成功清空无效数据";
    }


    //    2、向创建的集合中插入1万条学生数据
    @Autowired
    private StudentRandom random;
    @GetMapping("/save")
    public List<g01Student> saveAll(){
        List<g01Student> g01Students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            g01Student g01Student = random.create();
            g01Students.add(g01Student);
            studentService.saveStudent(g01Student);
        }
        return g01Students;
    }


    @GetMapping("/updateSex")
    public void update(){
//        4、把数据库中所有年龄为1到3岁的男性年龄修改为5岁，女性年龄修改为4岁。
        Update update = new Update();
        update.set("age","5");
        Query query = new Query(Criteria.where("age").gte(1).lte(3).and("sex").is("男"));

        UpdateResult updateResult = mongoTemplate.updateMulti(query,update,g01Student.class);
        long matchedCount = updateResult.getMatchedCount();
        long modifiedCount = updateResult.getModifiedCount();
        System.out.println(matchedCount+"符合条件的数据"+modifiedCount+"修改成功的数据");


        Update update1 = new Update();
        update.set("age","4");
        Query query1 = new Query(Criteria.where("age").gte(1).lte(3).and("sex").is("女"));
        UpdateResult updateResult1 = mongoTemplate.updateMulti(query1,update1,g01Student.class);
        long matchedCount1 = updateResult1.getMatchedCount();
        long modifiedCount1 = updateResult1.getModifiedCount();
        System.out.println(matchedCount1+"符合条件的数据"+modifiedCount1+"修改成功的数据");
    }




//    3、把数据库中所有年龄为0的数据删除。
    @GetMapping("/delete0")
    public void delete(){
        Query query = new Query();
        query.addCriteria(Criteria.where("age").is(0));
        mongoTemplate.remove(query,g01Student.class);
        System.out.println("删除成功");
    }


//    查询出所有18岁性马的男同学
    @GetMapping("/select1")
    public void select1(){
        Query query = new Query(Criteria.where("age").is(18));
        query.addCriteria(Criteria.where("name").regex("[马]"));
        List<g01Student> g01Students = mongoTemplate.find(query, g01Student.class);
        g01Students.forEach(System.out::println);
    }


//    查询出所有18岁的女同学和24岁男同学的信息

    @GetMapping("/select2")
    public void select2(){
        Query query = new Query(Criteria.where("age").in(18,24));
        query.addCriteria(Criteria.where("sex").in("男","女"));
        List<g01Student> g01Students = mongoTemplate.find(query, g01Student.class);
        g01Students.forEach(System.out::println);

    }

//    查询出系统中的专业名称
    @GetMapping("/select3")
    public void select3(){

        GroupOperation groupOperation = Aggregation.group("major");
         TypedAggregation<g01Student> aggregation = Aggregation.newAggregation(g01Student.class,groupOperation);
        AggregationResults<g01Student> aggregate = mongoTemplate.aggregate(aggregation,g01Student.class);
        List<g01Student> mappedResults = aggregate.getMappedResults();
        mappedResults.forEach(System.out::println);
    }

//    9、查询出系统中每个专业中姓张的学生人数
@GetMapping("/select4")
public void select4(){

    GroupOperation groupOperation = Aggregation.group("major");
    MatchOperation matchOperation = Aggregation.match(Criteria.where("name").regex("[张]"));
    TypedAggregation<g01Student> aggregation = Aggregation.newAggregation(g01Student.class,groupOperation,matchOperation);
    AggregationResults<g01Student> aggregate = mongoTemplate.aggregate(aggregation,g01Student.class);
    List<g01Student> mappedResults = aggregate.getMappedResults();
    System.out.println("姓张的人数："+mappedResults.size());
}
}
