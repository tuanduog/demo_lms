package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "duration")
    private Integer duration; // Kiểu int hoặc long tùy thuộc vào độ lớn dữ liệu

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "repeat_limit")
    private Integer repeatLimit; // Kiểu int tùy thuộc vào giới hạn lặp lại

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "assignment_type")
    private String assignmentType;

    public Assignment(String title, String description, LocalDateTime startTime, Integer duration, LocalDateTime endTime, Integer repeatLimit, LocalDateTime createdAt, LocalDateTime modifiedAt, String assignmentType) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.repeatLimit = repeatLimit;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.assignmentType = assignmentType;
    }
}
