package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MCQAnswerDTO {
    private Long id;
    private String answerContent;
    private Boolean isCorrect;
}
