package com.assignment.demo.controller;

import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.service.AssignmentService;
import com.assignment.demo.service.ScoreReportService;
import com.assignment.demo.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class SubmissionController {

    @Autowired
    SubmissionService submissionService;
    @Autowired
    ScoreReportService scoreReportService;
    @Autowired
    AssignmentService assignmentService;
    @GetMapping
    public List<Assignment> getAllAssignment(){
        return new ArrayList<>();
    }
    @GetMapping("/{id}")
    public Assignment getAssignmentDetail(@PathVariable int id){
        return new Assignment();
    }
    @PostMapping("/{id}")
    public List<QuestionSubmission> submitAssignment(@PathVariable Long id, @RequestBody List<QuestionAnswerDTO> answerList){
        Long scoreReportID= scoreReportService.addAndGetScoreReport(
                new ScoreReport(
                        assignmentService.getAssignmentByRefer(id),
                        new Student(),
                        LocalDateTime.now(),
                        "Pending"
                )
        );
        scoreReportService.addScoreVersion(
                new ScoreVersion(
                        scoreReportService.getScoreReportByRefer(scoreReportID),
                        null,
                        null,
                        LocalDateTime.now()
                )
        );
        submissionService.submitAnswer(id,scoreReportID,answerList);
        return new ArrayList<>();
    }

}
