package com.assignment.demo.service;

import com.assignment.demo.model.Assignment;
import com.assignment.demo.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public Assignment getAssignmentByRefer(Long id){
        return assignmentRepository.getReferenceById(id);
    }
    public List<Assignment> getAll(){
        return assignmentRepository.findAll();
    }
}
