package com.assignment.demo.repository;

import com.assignment.demo.model.QuestionMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionMaterialRepository extends JpaRepository<QuestionMaterial,Long> {
    List<QuestionMaterial> findAllByAssignmentSection_ID(Long assignmentSectionId);
}
