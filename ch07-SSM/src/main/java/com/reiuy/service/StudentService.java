package com.reiuy.service;

import com.reiuy.entity.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);

    List<Student> foundStudents();

}
