package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    private String title;
    private String description;
    private String assignmentType;
    private Integer duration;
    private LocalDateTime deadline;
}
