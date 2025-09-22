package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_submission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_submission_id")
    private Long ID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_report_id")
    private ScoreReport scoreReport;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_section_id")
    private AssignmentSection assignmentSection;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "student_answer")
    private String studentAnswer;

    @Column(name = "score")
    private Double score;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    public QuestionSubmission(ScoreReport scoreReport, AssignmentSection assignmentSection, Question question, String studentAnswer, Double score, Boolean isCorrect) {
        this.scoreReport = scoreReport;
        this.assignmentSection = assignmentSection;
        this.question = question;
        this.studentAnswer = studentAnswer;
        this.score = score;
        this.isCorrect = isCorrect;
    }
}
