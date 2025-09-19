package com.assignment.demo.repository;

import com.assignment.demo.model.AssignmentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentSectionRepository extends JpaRepository<AssignmentSection,Integer> {
    List<AssignmentSection> findByAssignmentId(long id);
}
