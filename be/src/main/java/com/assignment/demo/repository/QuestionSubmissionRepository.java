package com.assignment.demo.repository;

import com.assignment.demo.model.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission,Long> {
    List<QuestionSubmission> findByScoreReport_Id(String ScoreReport_Id);
}
