package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "score_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_report_id")
    private Long id;

    // FK: assignments_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    // FK: student_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "grading_status")
    private String gradingStatus;

    public ScoreReport(Assignment assignment, Student student, LocalDateTime createdAt, String gradingStatus) {
        this.assignment = assignment;
        this.student = student;
        this.createdAt = createdAt;
        this.gradingStatus = gradingStatus;
    }
}
