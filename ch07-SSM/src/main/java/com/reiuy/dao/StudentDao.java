package com.reiuy.dao;

import com.reiuy.entity.Student;

import java.util.List;

public interface StudentDao {

    int insertStudent(Student student);  //添加信息

    List<Student> selectStudents();      //查询全部学生
}
