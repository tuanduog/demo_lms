package com.assignment.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_material")
public class QuestionMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_material_id")
    private long ID;

    @Column(name = "url")
    private String url;
    @Column(name = "type")
    private String type;
}
