package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_material")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
