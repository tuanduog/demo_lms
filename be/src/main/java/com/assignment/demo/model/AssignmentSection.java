package com.assignment.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignment_section")
public class AssignmentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignments_section_id")
    private Long ID;

    // FK: assignments_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_id")
    private Assignment assignment;

    // FK: questions_material_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_material_id")
    private QuestionMaterial questionMaterial;

    @Column(name = "name")
    private String name;

    @Column(name = "question_type")
    private String questionType;
}
