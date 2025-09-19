package com.assignment.demo.repository;

import com.assignment.demo.model.QuestionMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMaterialRepository extends JpaRepository<QuestionMaterial,Integer> {
}
