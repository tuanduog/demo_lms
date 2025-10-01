package com.assignment.demo.service;

import com.assignment.demo.model.Student;
import com.assignment.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student getStudentByRefer(Long id){
        return studentRepository.getReferenceById(id);
    }
}
