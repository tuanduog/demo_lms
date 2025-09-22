package com.assignment.demo.service;

import com.assignment.demo.dto.GradingDTO;
import com.assignment.demo.model.QuestionSubmission;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.model.ScoreVersion;
import com.assignment.demo.repository.QuestionSubmissionRepository;
import com.assignment.demo.repository.ScoreReportRepository;
import com.assignment.demo.repository.ScoreVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        ScoreVersion scoreVersion= new ScoreVersion(
                scoreReportRepository.getReferenceById(id),
                grading.getScore(),
                grading.getReview(),
                LocalDateTime.now()
        );
        QuestionSubmission questionSubmission=questionSubmissionRepository.findByScoreReport_Id(id);
        questionSubmission.setScore(grading.getScore());
        questionSubmissionRepository.save(questionSubmission);

        ScoreVersion sv=scoreVersionRepository.findTopByScoreReport_IdOrderByModifiedAtDesc(id).orElse(null);
        if(sv!=null){
            scoreVersion.setScore(grading.getScore()+sv.getScore());
        }
        scoreVersionRepository.save(scoreVersion);

        ScoreReport scoreReport=scoreReportRepository.findById(id).orElse(null);
        if(scoreReport!=null){
            scoreReport.setGradingStatus("true");
            scoreReportRepository.save(scoreReport);
        }

        return "Completed";
    }
}
