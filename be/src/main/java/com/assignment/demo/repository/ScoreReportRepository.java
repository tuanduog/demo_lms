package com.assignment.demo.repository;

import com.assignment.demo.model.ScoreReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreReportRepository extends JpaRepository<ScoreReport, String> {
    List<ScoreReport> findByAssignment_Id(Long id);
}

