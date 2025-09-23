package com.assignment.demo.repository;

import com.assignment.demo.model.MCQAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCQAnswerRepository extends JpaRepository<MCQAnswer, Integer> {

}
