package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSectionDTO {
    private Long assignmentSectionId;
    private String sectionName;
    private List<QuestionMaterialDTO> questionMaterials;
}
