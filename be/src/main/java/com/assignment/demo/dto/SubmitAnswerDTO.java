package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswerDTO {
    private Map<Long, Map<Long, String>> blankAnswers;
    private Map<Long, String> mcqAnswers;
    private Map<Long, String> essayAnswers;
}
