package com.assignment.demo.service;

import com.assignment.demo.model.Assignment;
import com.assignment.demo.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public Assignment getAssignmentByRefer(int id){
        return assignmentRepository.getReferenceById(id);
    }
}
