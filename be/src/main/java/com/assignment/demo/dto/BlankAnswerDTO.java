package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlankAnswerDTO {
    private Long id;
    private String answerContent;
}
