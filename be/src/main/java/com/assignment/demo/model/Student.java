package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long ID;

    @Column(name = "student_name")
    private String studentName;

//    // FK: user_id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    // FK: class_id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "classes")
//    private Classes classes;
//
//    @Column(name = "dob")
//    private LocalDate dob;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "joined_at")
//    private LocalDateTime joinedAt;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;
//
//    @Column(name = "modified_by")
//    private String modifiedBy;

}