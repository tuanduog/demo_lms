package com.assignment.demo.repository;

import com.assignment.demo.model.AssignmentSectionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSectionSubmissionRepository extends JpaRepository<AssignmentSectionSubmission,Integer> {
}
