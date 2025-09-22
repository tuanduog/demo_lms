package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "score_version")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public ScoreVersion(ScoreReport scoreReport, Double score, String review, LocalDateTime modifiedAt) {
        this.scoreReport = scoreReport;
        this.score = score;
        this.review = review;
        this.modifiedAt = modifiedAt;
    }
}
