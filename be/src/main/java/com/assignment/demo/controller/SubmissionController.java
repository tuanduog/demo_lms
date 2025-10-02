package com.assignment.demo.controller;

import com.assignment.demo.dto.AssignmentFormBasicDTO;
import com.assignment.demo.dto.AssignmentFormDetailDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.dto.QuestionDisplayDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.repository.AssignmentRepository;
import com.assignment.demo.service.*;
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
    @Autowired
    StudentService studentService;
    @GetMapping
    public List<Assignment> getAllAssignment(){
        return assignmentService.getAll();
    }
    @GetMapping("/{id}")
    public AssignmentFormDetailDTO getAssignmentDetail(@PathVariable Long id){
        Assignment assignment= assignmentService.getOneById(id);
        List<QuestionDisplayDTO> questionList= questionService.getQuestionListByAssignment_Id(id);
        for( QuestionDisplayDTO question: questionList){
            if(question.getQuestionType().equalsIgnoreCase("mcq")){
                question.setAnswerList(questionService.findByQuestion_Id(question.getQuestionId()));
            }
        }
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
    public String submitAssignment(@PathVariable Long id, @RequestBody List<QuestionAnswerDTO> answerList){
        Long scoreReportID= scoreReportService.addAndGetScoreReport(
                new ScoreReport(
                        assignmentService.getAssignmentByRefer(id),
                        studentService.getStudentByRefer(21l),
                        LocalDateTime.now(),
                        "Pending"
                )
        );
        Long scoreVersionID=scoreReportService.addScoreVersion(
                new ScoreVersion(
                        scoreReportService.getScoreReportByRefer(scoreReportID),
                        null,
                        null,
                        LocalDateTime.now()
                )
        );
        submissionService.submitAnswer(id,scoreReportID,scoreVersionID,answerList);
        return "Completed";
    }

}
