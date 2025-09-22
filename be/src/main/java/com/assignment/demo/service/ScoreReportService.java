package com.assignment.demo.service;

import com.assignment.demo.dto.AssignmentBasicDTO;
import com.assignment.demo.dto.AssignmentDetailDTO;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.model.ScoreVersion;
import com.assignment.demo.repository.AssignmentRepository;
import com.assignment.demo.repository.QuestionSubmissionRepository;
import com.assignment.demo.repository.ScoreReportRepository;
import com.assignment.demo.repository.ScoreVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreReportService {
    @Autowired
    ScoreReportRepository scoreReportRepository;

    @Autowired
    ScoreVersionRepository scoreVersionRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    QuestionSubmissionRepository questionSubmissionRepository;
    public Long addAndGetScoreReport(ScoreReport scoreReport){
        return scoreReportRepository.save(scoreReport).getId();
    }

    public void addScoreVersion(ScoreVersion scoreVersion){
        scoreVersionRepository.save(scoreVersion);
    }
    public ScoreReport getScoreReportByRefer(Long id){
       return scoreReportRepository.getReferenceById(id);
    }
    public AssignmentDetailDTO getGradingAssignment(long scoreReportID){
        AssignmentDetailDTO assignment= scoreReportRepository.findAssignmentDetail(scoreReportID);
        assignment.setAnswerList(questionSubmissionRepository.findAllByScoreReport_Id(scoreReportID));
        return assignment;
    }
    public List<AssignmentBasicDTO> getAllGradingAssignment(){
        return scoreReportRepository.findAllAssignmentDetail();
    }
}
