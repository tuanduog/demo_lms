package com.assignment.demo.dto;

import com.assignment.demo.model.QuestionSubmission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDetailDTO {
    private long scoreReportID;
    private long studentID;
    private String title;
    private String description;
    private String assignmentType;
    private List<QuestionSubmissionDTO> answerList;
    private Double score;
    private String review;
    private LocalDateTime modifiedAt;
    private String gradingStatus;

    public AssignmentDetailDTO(long scoreReportID, long studentID, String title, String description, String assignmentType, Double score, String review, LocalDateTime modifiedAt, String gradingStatus) {
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
}
