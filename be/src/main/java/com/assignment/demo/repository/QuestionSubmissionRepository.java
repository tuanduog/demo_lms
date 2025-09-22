package com.assignment.demo.repository;

import com.assignment.demo.dto.QuestionSubmissionDTO;
import com.assignment.demo.model.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission,Integer> {
    QuestionSubmission findByScoreReport_Id(Long id);
    @Query("""
            SELECT new com.assignment.demo.dto.QuestionSubmissionDTO
            (qs.id, as.ID, q.content, qs.studentAnswer, qs.score, qs.isCorrect) 
            FROM QuestionSubmission qs 
            JOIN qs.assignmentSection as
            JOIN qs.question q
            
            
            """)
    List<QuestionSubmissionDTO> findAllByScoreReport_Id(Long id);
}
