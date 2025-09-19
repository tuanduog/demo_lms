package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
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
}
