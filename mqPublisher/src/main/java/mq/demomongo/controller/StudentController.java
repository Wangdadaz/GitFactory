package mq.demomongo.controller;//wangDD


import mq.demomongo.po.g01Student;

import mq.demomongo.utils.StudentRandom;
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
    private RabbitTemplate rabbitTemplate;



    @Autowired
    private StudentRandom random;
    @GetMapping("/push")
    public List<g01Student> saveAll(){
        List<g01Student> g01Students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            g01Student g01Student = random.create();
            g01Students.add(g01Student);
            String queueName = "student.queue";
            rabbitTemplate.convertAndSend(queueName, g01Student);
        }
        return g01Students;
    }
}
