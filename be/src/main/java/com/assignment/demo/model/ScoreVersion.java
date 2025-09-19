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

    public ScoreVersion() {
    }

    public ScoreVersion(ScoreReport scoreReport, Double score, String review, LocalDateTime modifiedAt) {
        this.scoreReport = scoreReport;
        this.score = score;
        this.review = review;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScoreReport getScoreReport() {
        return scoreReport;
    }

    public void setScoreReport(ScoreReport scoreReport) {
        this.scoreReport = scoreReport;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
