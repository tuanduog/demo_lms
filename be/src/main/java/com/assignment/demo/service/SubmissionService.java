package com.assignment.demo.service;

import com.assignment.demo.dto.CorrectAnswerDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
    @Autowired
    ScoreReportRepository scoreReportRepository;
    @Autowired
    ScoreVersionRepository scoreVersionRepository;
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
    @Autowired
    MCQAnswerService mcqAnswerService;

    public void submitAnswer(Long assignmentID, Long scoreReportID, Long scoreVersionID,List<QuestionAnswerDTO> list){
        Set<Long> sectionIds = list.stream()
                .map(QuestionAnswerDTO::getSectionID)
                .collect(Collectors.toSet());

        for (Long sectionId : sectionIds) {
            AssignmentSection as = assignmentSectionRepository.getReferenceById(sectionId);
            assignmentSectionSubmissionRepository.save(new AssignmentSectionSubmission(
                    as,
                    null,
                    LocalDateTime.now()
            ));
            if (as.getQuestionType().equalsIgnoreCase("MCQ") || as.getQuestionType().equalsIgnoreCase("Blank")) {
                submitAutoGrading(scoreReportID, sectionId, list, assignmentID, scoreVersionID);
            } else {
                submitManualGrading(scoreReportID, sectionId, list);
            }
        }

    }
    public void submitAutoGrading(Long scoreReportID, long sectionID, List<QuestionAnswerDTO> list, Long assignmentID, Long scoreVersionID){
        List<QuestionSubmission> questionSubmissions= new ArrayList<>();
        List<CorrectAnswerDTO> answerList=null;
        System.out.println("second id:"+ assignmentID);
        Map<Long, String> answerMap=mcqAnswerService.getCorrectAnswerByAssignment_Id(assignmentID);
        System.out.println("mapp:"+answerMap);
        double count=0;
        try{
            for(QuestionAnswerDTO answer: list ){
                if(answer.getSectionID()==sectionID){
                    if(answer.getStudentAnswer()!=null || answer.getFile() !=null){
                        questionSubmissions.add(new QuestionSubmission(
                                scoreReportRepository.getReferenceById(scoreReportID),
                                assignmentSectionRepository.getReferenceById(answer.getSectionID()),
                                questionRepository.getReferenceById(Math.toIntExact(answer.getQuestionID())),
                                answer.getStudentAnswer(),
                                (answerMap.get(answer.getQuestionID()).equalsIgnoreCase(answer.getStudentAnswer())? 1.0:0.0),
                                answerMap.get(answer.getQuestionID()).equalsIgnoreCase(answer.getStudentAnswer()),
                                null
                        ));
                        if(answerMap.get(answer.getQuestionID()).equalsIgnoreCase(answer.getStudentAnswer())){
                            count++;
                        }
                    }
                    else{
                        questionSubmissions.add(new QuestionSubmission(
                                scoreReportRepository.getReferenceById(scoreReportID),
                                assignmentSectionRepository.getReferenceById(answer.getSectionID()),
                                questionRepository.getReferenceById(Math.toIntExact(answer.getQuestionID())),
                                answer.getStudentAnswer(),
                                0.0,
                                false,
                                null
                        ));
                    }


                }
            }
            questionSubmissionRepository.saveAll(questionSubmissions);
            ScoreVersion newScore= scoreVersionRepository.findById(scoreVersionID).orElse(null);
            if( newScore!= null){
                newScore.setScore(count);
                scoreVersionRepository.save(newScore);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void submitManualGrading(Long scoreReportID, long sectionID, List<QuestionAnswerDTO> list){
        List<QuestionSubmission> questionSubmissions= new ArrayList<>();
        for(QuestionAnswerDTO answer: list ){
            if(answer.getSectionID()==sectionID){
                questionSubmissions.add(new QuestionSubmission(
                    scoreReportRepository.getReferenceById(scoreReportID),
                        assignmentSectionRepository.getReferenceById(answer.getSectionID()),
                        questionRepository.getReferenceById(Math.toIntExact(answer.getQuestionID())),
                        answer.getStudentAnswer(),
                        null,
                        null,
                        "hehe"
                ));
            }
        }
        questionSubmissionRepository.saveAll(questionSubmissions);
    }
}
