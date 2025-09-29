package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlankQuestionDTO {
    private Long id;
    private Long blankSlot;
    List<BlankAnswerDTO> blankAnswers;
}
