package com.assignment.demo.service;

import com.assignment.demo.dto.AssignmentRequest;
import com.assignment.demo.model.Assignment;
import com.assignment.demo.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment addAssignment(@RequestBody AssignmentRequest assignment){
        Assignment newAssignment = new Assignment();
        newAssignment.setTitle(assignment.getTitle());
        newAssignment.setDescription(assignment.getDescription());
        newAssignment.setDuration(assignment.getDuration());
        newAssignment.setEndTime(assignment.getDeadline());
        return assignmentRepository.save(newAssignment);
    }

    public Assignment getOneToTest(Long id){
        return assignmentRepository.findById(id).get();
    }
}
