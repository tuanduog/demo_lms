package com.assignment.demo.repository;

import com.assignment.demo.model.Question;
import com.assignment.demo.model.QuestionMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
