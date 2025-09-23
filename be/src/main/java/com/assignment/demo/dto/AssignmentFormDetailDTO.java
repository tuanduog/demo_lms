package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentFormDetailDTO {
    private Long assignmentId;
    private String title;
    private String description;
    private Integer repeatLimit;
    private String assignmentType;
    private List<QuestionDisplayDTO> questionList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration;
    private LocalDateTime createdAt;
}
