package mq.demomongo.service;//wangDD


import mq.demomongo.dao.StudentMapper;
import mq.demomongo.po.g01Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//2022-09-2022/9/8-22:52
@Service  //声明这个类为service bean
public class StudentService {

    @Autowired
    private StudentMapper student;

    public void saveStudent(g01Student s){
        student.save(s);
    }
    public void deleteStudentById(g01Student s){
        student.deleteById(s);
    }
    public List<g01Student> listStudent(){
        List<g01Student> all = student.findAll();
        return all;
    }

    public boolean delete(){

        student.deleteAll();
        return true;
    }
}
