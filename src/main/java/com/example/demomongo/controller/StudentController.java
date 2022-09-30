package com.example.demomongo.controller;//wangDD

import com.example.demomongo.po.g01Student;
import com.example.demomongo.service.StudentService;
import com.example.demomongo.utils.StudentRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//2022-09-2022/9/8-23:19
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<g01Student> getAll(){
        return studentService.listStudent();
    }

    @GetMapping("/delete")
    public String deleteAll(){

        studentService.delete();
        return "成功清空无效数据";
    }


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
}
