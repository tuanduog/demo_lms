package com.assignment.demo.controller;

import com.assignment.demo.dto.AssignmentDetailDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.service.ScoreReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grading")
public class GradingController {

    @Autowired
    ScoreReportService scoreReportService;
    @GetMapping
    public List<AssignmentDetailDTO> getAllAssignment(){
        return new ArrayList<>();
    }
    @GetMapping("/{id}")
    public AssignmentDetailDTO getAssignmentDetail(@PathVariable long id){
        return scoreReportService.getGradingAssignment(id);
    }
    @PostMapping("/{id}")
    public void submitGrading(@PathVariable int id){

    }

}
