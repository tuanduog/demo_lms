package com.assignment.demo.service;

import com.assignment.demo.dto.GradingDTO;
import com.assignment.demo.dto.QuestionScoreDTO;
import com.assignment.demo.model.QuestionSubmission;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.model.ScoreVersion;
import com.assignment.demo.repository.QuestionSubmissionRepository;
import com.assignment.demo.repository.ScoreReportRepository;
import com.assignment.demo.repository.ScoreVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradingService {
    @Autowired
    ScoreReportRepository scoreReportRepository;

    @Autowired
    ScoreVersionRepository scoreVersionRepository;
    @Autowired
    QuestionSubmissionRepository questionSubmissionRepository;

    //addScore
    //update score in questionSubmission
    //add scoreVersion according to the latest modified_at
    //Change the grading status in report
    public String addScore(Long id, GradingDTO grading){
        double score=0;
        QuestionSubmission questionSubmission;
        List<QuestionSubmission> submissionList= new ArrayList<>();
        for (QuestionScoreDTO qs: grading.getScoreList()){
            questionSubmission= questionSubmissionRepository.findById(qs.getQuestionSubmissionId()).orElse(null);

            if( questionSubmission==null){
                continue;
            }
            submissionList.add(questionSubmission);
            questionSubmission.setScore(qs.getScore());
            questionSubmission.setQuestionReview("heheee");

            score+=qs.getScore();
        }
        questionSubmissionRepository.saveAll(submissionList);
        ScoreVersion sv=scoreVersionRepository.findTopByScoreReport_IdOrderByModifiedAtDesc(id).orElse(null);
//        if(sv!=null){
//            sv.setScore(score+sv.getScore());
//            sv.setReview(grading.getReview());
//            scoreVersionRepository.save(sv);
//        }
//        else{
//            ScoreVersion scoreVersion= new ScoreVersion(
//                    scoreReportRepository.getReferenceById(id),
//                    score,
//                    grading.getReview(),
//                    LocalDateTime.now()
//            );
//            scoreVersionRepository.save(scoreVersion);
//        }
        sv.setScore(score);
        sv.setReview(grading.getReview());
        scoreVersionRepository.save(sv);
        ScoreReport scoreReport=scoreReportRepository.findById(id).orElse(null);
        if(scoreReport!=null){
            scoreReport.setGradingStatus("Completed");
            scoreReportRepository.save(scoreReport);
        }

        return "Completed";
    }
}
