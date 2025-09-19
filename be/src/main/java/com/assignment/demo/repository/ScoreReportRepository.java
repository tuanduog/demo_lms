package com.assignment.demo.repository;

import com.assignment.demo.model.ScoreReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreReportRepository extends JpaRepository<ScoreReport,Integer> {
}
