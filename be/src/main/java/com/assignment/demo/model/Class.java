package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @ManyToMany(mappedBy = "classes")
    private Set<Student> students = new HashSet<>();

    @Column(name = "class_code", unique = true, nullable = false)
    private String classCode;

    @Column(name = "max_student")
    private Integer maxStudent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

}
