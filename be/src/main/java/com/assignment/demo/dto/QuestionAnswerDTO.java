package com.assignment.demo.dto;

public class QuestionAnswerDTO {
    private long sectionID;
    private long questionID;
    private String studentAnswer;
    private String questionScore;

    public QuestionAnswerDTO() {
    }

    public QuestionAnswerDTO(long sectionID, long questionID, String studentAnswer, String questionScore) {
        this.sectionID = sectionID;
        this.questionID = questionID;
        this.studentAnswer = studentAnswer;
        this.questionScore = questionScore;
    }

    public long getSectionID() {
        return sectionID;
    }

    public void setSectionID(long sectionID) {
        this.sectionID = sectionID;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }
}
