package com.assignment.demo.repository;

import com.assignment.demo.model.MCQAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MCQAnswerRepository extends JpaRepository<MCQAnswer, Integer> {
    List<MCQAnswer> findByQuestion_QuestionId(Long questionId);
}
