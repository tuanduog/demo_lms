package com.assignment.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_submission")
public class QuestionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questions_submission_id")
    private Long ID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_report_id")
    private ScoreReport scoreReport;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_section_submission_id")
    private AssignmentSectionSubmission assignmentSectionSubmission;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id")
    private Question question;

    @Column(name = "student_answer")
    private String studentAnswer;

    @Column(name = "score")
    private Double score;

    @Column(name = "is_correct")
    private Boolean isCorrect;

}
