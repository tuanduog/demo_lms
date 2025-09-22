package com.assignment.demo.controller;

import com.assignment.demo.model.Assignment;
import com.assignment.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    AssignmentService assignmentService;
    @GetMapping("/")
    public String helllo(){
        return "hello";
    }
    @GetMapping("/get")
    public List<Assignment> getAssign(){
        return assignmentService.getAll();
    }

}
