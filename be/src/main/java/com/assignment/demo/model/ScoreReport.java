package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "score_report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreReport {
    @Id
    @Column(name = "score_report_id")
    private String id;

    // FK: assignments_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_id")
    private Assignment assignment;

    // FK: student_id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    private Student student;

    @Column(name = "auto_grading_score")
    private Double autoGradingScore;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "grading_status")
    private String gradingStatus;

}
