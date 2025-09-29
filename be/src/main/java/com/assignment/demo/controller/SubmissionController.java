package com.assignment.demo.controller;

import com.assignment.demo.dto.QuestionSubmissionDTO;
import com.assignment.demo.dto.SubmitAnswerDTO;
import com.assignment.demo.model.*;
import com.assignment.demo.repository.*;
import com.assignment.demo.service.MCQAnswerService;
import com.assignment.demo.service.QuestionService;
import com.assignment.demo.service.ScoreReportService;
import com.assignment.demo.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MCQAnswerService  mcqAnswerService;

    @Autowired
    private QuestionSubmissionRepository questionSubmissionRepository;

    @Autowired private BlankAnswerRepository  blankAnswerRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ScoreReportRepository  scoreReportRepository;

    @Autowired
    private ScoreReportService scoreReportService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/submit/{asignId}")
    public ResponseEntity<?> submitSubmission(@PathVariable Long asignId, @RequestBody SubmitAnswerDTO answer) {
        System.out.println("Blank answer: " + answer.getBlankAnswers());
        System.out.println("MCQ answer: " + answer.getMcqAnswers());
        System.out.println("Essay answer: " + answer.getEssayAnswers());

        if(!scoreReportService.checkRepeat(asignId)){
            return ResponseEntity.badRequest().body("Bạn đã đạt giới hạn số lần làm bài!");
        }

        double total = 0.0;
        String id = UUID.randomUUID().toString();
        ScoreReport scoreReport = new ScoreReport();
        scoreReport.setId(id);
        scoreReport.setAssignment(assignmentRepository.findById(asignId).orElseThrow());
        scoreReport.setCreatedAt(LocalDateTime.now());
        scoreReportRepository.save(scoreReport);

        // xử lý blank question
        for (Map.Entry<Long, Map<Long, String>> blankans : answer.getBlankAnswers().entrySet()) {
            Long questionId = blankans.getKey();
            Map<Long, String> blankAnswers = blankans.getValue();

            Question q = questionRepository.findById(questionId).orElseThrow();
            double score = q.getScore();

            // 1 câu hỏi - n blank slot
            List<BlankQuestion> blankQuestions = submissionService.findBlankQuestion(questionId);
            double scorePerAns = score / blankQuestions.size();

            for (Map.Entry<Long, String> b : blankAnswers.entrySet()) {
                Long slotId = b.getKey();     // slot trong {blank1}, {blank2}
                String studentAnswer = b.getValue();

                for (BlankQuestion bq : blankQuestions) {
                    if (bq.getBlankSlot().equals(slotId)) {
                        // tìm đáp án đúng
                        BlankAnswer ba = blankAnswerRepository.findByBlankQuestion_Id(bq.getId());

                        QuestionSubmission qs = new QuestionSubmission();
                        qs.setQuestion(q);
                        qs.setStudentAnswer(studentAnswer);
                        qs.setCorrectAnswer(ba.getAnswerContent().trim());
                        qs.setExplaination(q.getExplaination());
                        qs.setScoreReport(scoreReport);

                        if (studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(ba.getAnswerContent().trim())) {
                            qs.setScore(scorePerAns);
                            total += scorePerAns;
                        } else {
                            qs.setScore(0.0);
                        }

                        questionSubmissionRepository.save(qs);
                    }
                }
            }
        }

        // xử lý mcq question
        for(Map.Entry<Long, String> mcq : answer.getMcqAnswers().entrySet()){
            Long questionId = mcq.getKey();
            MCQAnswer correctAnswer = mcqAnswerService.checkCorrectAns(questionId);
            Question q = questionRepository.findById(questionId).orElseThrow();
            QuestionSubmission qs = new QuestionSubmission();
            qs.setQuestion(q);
            qs.setExplaination(q.getExplaination());
            qs.setScore(q.getScore());
            qs.setCorrectAnswer(correctAnswer.getAnswerContent().trim());
            qs.setStudentAnswer(mcq.getValue().trim());
            qs.setScoreReport(scoreReport);
            questionSubmissionRepository.save(qs);

            String mcqAnswerContent = correctAnswer.getAnswerContent().trim();
            if(mcqAnswerContent.trim().equalsIgnoreCase(mcq.getValue().trim())){
                total += q.getScore();
            }

        }
        boolean check = questionService.checkType(asignId);
        scoreReport.setAutoGradingScore(total);
        if(check == false){
            scoreReport.setGradingStatus("Pending");
        } else {
            scoreReport.setGradingStatus("Success");
        }
        scoreReportRepository.save(scoreReport);
        Map<String, String> result =  new HashMap<String, String>();
        result.put("score", String.valueOf(total));
        result.put("scoreId", scoreReport.getId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get-submission/{scoreReportId}")
    public ResponseEntity<?> getSubmission(@PathVariable String scoreReportId){
        List<QuestionSubmission> qs = questionSubmissionRepository.findByScoreReport_Id(scoreReportId);
        List<QuestionSubmissionDTO> dtoList = qs.stream().map(q -> {
            QuestionSubmissionDTO dto = new QuestionSubmissionDTO();
            dto.setId(q.getID());
            dto.setScore(q.getScore());
            dto.setExplaination(q.getExplaination());
            dto.setCorrectAns(q.getCorrectAnswer());
            dto.setStudentAns(q.getStudentAnswer());
            dto.setQuestionId(q.getQuestion().getQuestionId());
            return dto;
        }).toList();
        return ResponseEntity.ok(dtoList);
    }
}
