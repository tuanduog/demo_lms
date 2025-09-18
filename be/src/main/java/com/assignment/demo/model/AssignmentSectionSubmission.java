package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_section_subission")
public class AssignmentSectionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignments_section_submission_id")
    private Long ID;

    // FK: assignments_section_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_section_id")
    private AssignmentSection assignmentSection;

    @Column(name = "section_score")
    private Double sectionScore;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

}
