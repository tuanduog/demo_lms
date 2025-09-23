package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionMaterialDTO {
    private Long questionMaterialId;
    private String url;
    private String type;
    private Integer quantity;
    private List<QuestionDTO> questions;
}
