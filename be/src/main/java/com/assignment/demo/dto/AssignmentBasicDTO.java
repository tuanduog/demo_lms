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
public class AssignmentBasicDTO {
    private long scoreReportID;
    private long studentID;
    private String title;
    private String assignmentType;
    private Double score;
    private String review;
    private LocalDateTime modifiedAt;
    private String gradingStatus;
}
