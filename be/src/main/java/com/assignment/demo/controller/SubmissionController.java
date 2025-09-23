package com.assignment.demo.controller;

import com.assignment.demo.dto.AssignmentFormDetailDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.dto.QuestionDisplayDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.service.AssignmentService;
import com.assignment.demo.service.QuestionService;
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
    @Autowired
    QuestionService questionService;
    @GetMapping
    public List<Assignment> getAllAssignment(){
        return new ArrayList<>();
    }
    @GetMapping("/{id}")
    public AssignmentFormDetailDTO getAssignmentDetail(@PathVariable Long id){
        Assignment assignment= assignmentService.getOneById(id);
        System.out.println("assignent: "+ assignment.toString());
        List<QuestionDisplayDTO> questionList= questionService.getQuestionListByAssignment_Id(id);
        return new AssignmentFormDetailDTO(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getRepeatLimit(),
                assignment.getAssignmentType(),
                questionList,
                assignment.getStartTime(),
                assignment.getEndTime(),
                assignment.getDuration(),
                assignment.getCreatedAt()
        );
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
