package com.assignment.demo.dto;

import com.assignment.demo.model.QuestionSubmission;

import java.time.LocalDateTime;
import java.util.List;

public class AssignmentDetailDTO {
    private long scoreReportID;
    private long studentID;
    private String title;
    private String description;
    private String assignmentType;
    private List<QuestionSubmission> answerList;
    private String score;
    private String review;
    private LocalDateTime modifiedAt;
    private String gradingStatus;

    public AssignmentDetailDTO() {
    }

    public AssignmentDetailDTO(long scoreReportID, long studentID, String title, String description, String assignmentType, String score, String review, LocalDateTime modifiedAt, String gradingStatus) {
        this.scoreReportID = scoreReportID;
        this.studentID = studentID;
        this.title = title;
        this.description = description;
        this.assignmentType = assignmentType;
        this.score = score;
        this.review = review;
        this.modifiedAt = modifiedAt;
        this.gradingStatus = gradingStatus;
    }

    public List<QuestionSubmission> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<QuestionSubmission> answerList) {
        this.answerList = answerList;
    }

    public long getScoreReportID() {
        return scoreReportID;
    }

    public void setScoreReportID(long scoreReportID) {
        this.scoreReportID = scoreReportID;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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

    public String getGradingStatus() {
        return gradingStatus;
    }

    public void setGradingStatus(String gradingStatus) {
        this.gradingStatus = gradingStatus;
    }
}
