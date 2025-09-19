package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_section_subission")
public class AssignmentSectionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_section_submission_id")
    private Long ID;

    // FK: assignments_section_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_section_id")
    private AssignmentSection assignmentSection;

    @Column(name = "section_score")
    private Double sectionScore;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    public AssignmentSectionSubmission() {
    }

    public AssignmentSectionSubmission(AssignmentSection assignmentSection, Double sectionScore, LocalDateTime savedAt) {
        this.assignmentSection = assignmentSection;
        this.sectionScore = sectionScore;
        this.savedAt = savedAt;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public AssignmentSection getAssignmentSection() {
        return assignmentSection;
    }

    public void setAssignmentSection(AssignmentSection assignmentSection) {
        this.assignmentSection = assignmentSection;
    }

    public Double getSectionScore() {
        return sectionScore;
    }

    public void setSectionScore(Double sectionScore) {
        this.sectionScore = sectionScore;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
