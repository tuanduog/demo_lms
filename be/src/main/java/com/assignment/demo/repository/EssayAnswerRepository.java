package com.assignment.demo.repository;

import com.assignment.demo.model.EssayAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayAnswerRepository extends JpaRepository<EssayAnswer, Long> {
}
