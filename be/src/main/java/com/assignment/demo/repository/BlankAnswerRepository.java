package com.assignment.demo.repository;

import com.assignment.demo.model.BlankAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlankAnswerRepository extends JpaRepository<BlankAnswer,Long> {
    BlankAnswer findByBlankQuestion_Id(Long blankQuestionId);
}
