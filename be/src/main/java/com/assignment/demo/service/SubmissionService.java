package com.assignment.demo.service;

import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    ScoreReportRepository scoreReportRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AssignmentSectionRepository assignmentSectionRepository;
    @Autowired
    AssignmentSectionSubmissionRepository assignmentSectionSubmissionRepository;
    @Autowired
    QuestionSubmissionRepository questionSubmissionRepository;

    public void submitAnswer(int assignmentID, int scoreReportID, List<QuestionAnswerDTO> list){
        List<AssignmentSection> sectionList= assignmentSectionRepository.findByAssignmentId(assignmentID);
        for(AssignmentSection as: sectionList){
            assignmentSectionSubmissionRepository.save(new AssignmentSectionSubmission(
                    as,null, LocalDateTime.now()
            ));
            if(as.getQuestionType().equalsIgnoreCase("MultipleChoice")||
                    as.getQuestionType().equalsIgnoreCase("Blank")){
                submitAutoGrading(scoreReportID,as.getID(),list);
            }
            else{
                submitManualGrading(scoreReportID,as.getID(),list);
            }
        }
    }
    public void submitAutoGrading(int scoreReportID, long sectionID, List<QuestionAnswerDTO> list){
        List<QuestionSubmission> questionSubmissions= new ArrayList<>();
        for(QuestionAnswerDTO answer: list ){
            if(answer.getSectionID()==sectionID){
                questionSubmissions.add(new QuestionSubmission(
                        scoreReportRepository.getReferenceById(scoreReportID),
                        assignmentSectionRepository.getReferenceById(Math.toIntExact(answer.getSectionID())),
                        questionRepository.getReferenceById(Math.toIntExact(answer.getQuestionID())),
                        answer.getStudentAnswer(),
                        null,
                        null
                ));
            }
        }
        questionSubmissionRepository.saveAll(questionSubmissions);
    }
    public void submitManualGrading(int scoreReportID, long sectionID, List<QuestionAnswerDTO> list){
        List<QuestionSubmission> questionSubmissions= new ArrayList<>();
        for(QuestionAnswerDTO answer: list ){
            if(answer.getSectionID()==sectionID){
                questionSubmissions.add(new QuestionSubmission(
                    scoreReportRepository.getReferenceById(scoreReportID),
                        assignmentSectionRepository.getReferenceById(Math.toIntExact(answer.getSectionID())),
                        questionRepository.getReferenceById(Math.toIntExact(answer.getQuestionID())),
                        answer.getStudentAnswer(),
                        null,
                        null
                ));
            }
        }
        questionSubmissionRepository.saveAll(questionSubmissions);
    }
}
