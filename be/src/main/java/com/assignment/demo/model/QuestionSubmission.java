package com.assignment.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_submission")
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

    public QuestionSubmission() {
    }

    public QuestionSubmission(ScoreReport scoreReport, AssignmentSection assignmentSection, Question question, String studentAnswer, Double score, Boolean isCorrect) {
        this.scoreReport = scoreReport;
        this.assignmentSection = assignmentSection;
        this.question = question;
        this.studentAnswer = studentAnswer;
        this.score = score;
        this.isCorrect = isCorrect;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public ScoreReport getScoreReport() {
        return scoreReport;
    }

    public void setScoreReport(ScoreReport scoreReport) {
        this.scoreReport = scoreReport;
    }

    public AssignmentSection getAssignmentSection() {
        return assignmentSection;
    }

    public void setAssignmentSection(AssignmentSection assignmentSection) {
        this.assignmentSection = assignmentSection;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
