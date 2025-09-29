package com.assignment.demo.dto;

import com.assignment.demo.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSubmissionDTO {
    private Long id;
    private String correctAns;
    private String explaination;
    private Double score;
    private String studentAns;
    private Long questionId;
}
