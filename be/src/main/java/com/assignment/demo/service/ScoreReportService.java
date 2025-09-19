package com.assignment.demo.service;

import com.assignment.demo.dto.AssignmentDetailDTO;
import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.model.ScoreVersion;
import com.assignment.demo.repository.AssignmentRepository;
import com.assignment.demo.repository.ScoreReportRepository;
import com.assignment.demo.repository.ScoreVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreReportService {
    @Autowired
    ScoreReportRepository scoreReportRepository;

    @Autowired
    ScoreVersionRepository scoreVersionRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    public int addAndGetScoreReport(ScoreReport scoreReport){
        return Math.toIntExact(scoreReportRepository.save(scoreReport).getId());
    }

    public void addScoreVersion(ScoreVersion scoreVersion){
        scoreVersionRepository.save(scoreVersion);
    }
    public ScoreReport getScoreReportByRefer(int id){
       return scoreReportRepository.getReferenceById(id);
    }
    public AssignmentDetailDTO getGradingAssignment(long scoreReportID){
        return scoreReportRepository.findAssignmentDetail(scoreReportID);
    }
}
