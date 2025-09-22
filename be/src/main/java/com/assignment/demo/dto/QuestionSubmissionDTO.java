package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSubmissionDTO {
    private Long questionSubmissionId;
    private Long assignmentSectionId;
    private String questionContent;
    private String studentAnswer;
    private Double score;
    private Boolean isCorrect;
}
