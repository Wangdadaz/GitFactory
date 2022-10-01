package mq.demomongo.controller;//wangDD


import mq.demomongo.po.g01Student;
import mq.demomongo.service.StudentService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/all")
    public List<g01Student> getAll(){
        return studentService.listStudent();
    }

    @GetMapping("/delete")
    public String deleteAll(){

        studentService.delete();
        return "成功清空无效数据";
    }



}
