package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long Id;
    private String type;
    private String questionContent;
    private String explanation;
    private Double score;
    List<BlankQuestionDTO> blankQuestions;
    List<MCQAnswerDTO> mcqAnswers;
}
