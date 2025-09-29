package com.assignment.demo.repository;

import com.assignment.demo.model.BlankQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlankQuestionRepository extends JpaRepository<BlankQuestion,Long> {
    List<BlankQuestion> findByQuestion_QuestionId(Long questionId);
}
