package com.reiuy.service.impl;

import com.reiuy.dao.StudentDao;
import com.reiuy.entity.Student;
import com.reiuy.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service    //创建对象
public class StudentServiceImpl implements StudentService {
    //引用类型的自动注入@Resource或者@Autowired

    @Resource
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        System.out.println(student.getName()+student.getAge());
        int nums = studentDao.insertStudent(student);

        return nums;
    }

    @Override
    public List<Student> foundStudents() {
        return studentDao.selectStudents();
    }
}
