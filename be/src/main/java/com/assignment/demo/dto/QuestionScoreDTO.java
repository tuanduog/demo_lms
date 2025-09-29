package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionScoreDTO {
    private Long questionSubmissionId;
    private Double score;
}
