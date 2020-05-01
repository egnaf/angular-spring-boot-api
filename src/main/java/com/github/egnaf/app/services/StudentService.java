package com.github.egnaf.app.services;

import com.github.egnaf.app.models.Student;

import java.util.List;

public interface StudentService {

    Student saveOrUpdate(Student student);

    List<Student> findAll();

    Student findById(String id);

    void deleteById(String id);
}
