package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "score_version")
public class ScoreVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long id;

    // FK: score_report_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_report_id")
    private ScoreReport scoreReport;

    @Column(name = "score")
    private Double score;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
