package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDisplayDTO {
    private Long questionId;
    private Long assignmentSectionId;
    private String questionType;
    private String materialURL;
    private String questionContent;
    private List<String> answerList;

    public QuestionDisplayDTO(Long questionId, Long assignmentSectionId, String questionType, String materialURL, String questionContent) {
        this.questionId = questionId;
        this.assignmentSectionId = assignmentSectionId;
        this.questionType = questionType;
        this.materialURL = materialURL;
        this.questionContent = questionContent;
    }
}
