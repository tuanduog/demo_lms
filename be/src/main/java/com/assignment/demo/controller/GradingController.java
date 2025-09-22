package com.assignment.demo.controller;

import com.assignment.demo.dto.AssignmentBasicDTO;
import com.assignment.demo.dto.AssignmentDetailDTO;
import com.assignment.demo.dto.GradingDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.service.GradingService;
import com.assignment.demo.service.ScoreReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grading")
@CrossOrigin(origins = "http://localhost:3000")
public class GradingController {

    @Autowired
    ScoreReportService scoreReportService;
    @Autowired
    GradingService gradingService;

    @GetMapping
    public List<AssignmentBasicDTO> getAllAssignment(){
        return scoreReportService.getAllGradingAssignment();
    }
    @GetMapping("/{id}")
    public AssignmentDetailDTO getAssignmentDetail(@PathVariable long id){
        return scoreReportService.getGradingAssignment(id);
    }
    @PostMapping("/{id}")
    public String submitGrading(@PathVariable Long id, @RequestBody GradingDTO gradingRequest){
        String result=gradingService.addScore(id,gradingRequest);
        if(result.equalsIgnoreCase("Completed")){
            return result;
        }
        else{
            return "error while add to the db";
        }
    }

}
