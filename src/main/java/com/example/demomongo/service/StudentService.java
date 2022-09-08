package com.example.demomongo.service;//wangDD

import com.example.demomongo.dao.StudentMapper;
import com.example.demomongo.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//2022-09-2022/9/8-22:52
@Service  //声明这个类为service bean
public class StudentService {

    @Autowired
    private StudentMapper student;

    public void saveStudent(Student s){
        student.save(s);
    }
    public void deleteStudentById(Student s){
        student.deleteById(s);
    }
    public List<Student> listStudent(){
        List<Student> all = student.findAll();
        return all;
    }
}
