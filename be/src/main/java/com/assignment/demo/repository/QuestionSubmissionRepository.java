package com.assignment.demo.repository;

import com.assignment.demo.model.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission,Integer> {
}
