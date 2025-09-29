package com.assignment.demo.service;

import com.assignment.demo.model.ScoreReport;
import com.assignment.demo.repository.ScoreReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreReportService {
    @Autowired
    private ScoreReportRepository scoreReportRepository;

    public Boolean checkRepeat(Long asignId){
        List<ScoreReport> sr = scoreReportRepository.findByAssignment_Id(asignId);
        if(sr.size() <= 2){
            return true;
        }
        return false;
    }
}
