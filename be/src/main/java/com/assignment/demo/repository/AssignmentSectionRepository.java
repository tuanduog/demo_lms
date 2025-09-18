package com.assignment.demo.repository;

import com.assignment.demo.model.AssignmentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSectionRepository extends JpaRepository<AssignmentSection,Integer> {
}
