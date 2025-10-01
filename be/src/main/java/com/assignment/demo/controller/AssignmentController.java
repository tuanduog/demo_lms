package com.assignment.demo.controller;

import com.assignment.demo.dto.AssignmentRequest;
import com.assignment.demo.model.Assignment;
import com.assignment.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignment")
@CrossOrigin(origins = "http://localhost:5173")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/add-assignment")
    public ResponseEntity<Assignment> addAssignment(@RequestBody AssignmentRequest assignment){
        if(assignment != null){
            Assignment as = assignmentService.addAssignment(assignment);
            return ResponseEntity.ok(as);
        }
        return ResponseEntity.badRequest().build();
    }


}