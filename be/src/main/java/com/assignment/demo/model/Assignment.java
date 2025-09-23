package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignments_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "assignment_value")
    private Integer assignment_value;

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

    @Column(name = "assignments_type")
    private String assignmentType;
}
