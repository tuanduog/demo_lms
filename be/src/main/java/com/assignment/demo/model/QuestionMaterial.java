package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "question_material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_material_id")
    private long ID;

    @OneToMany(mappedBy = "questionMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_section_id")
    private AssignmentSection assignmentSection;

    @Column(name = "url", columnDefinition = "TEXT")
    private String url;
    @Column(name = "type")
    private String type;
    @Column(name = "quantity")
    private int quantity;
}
