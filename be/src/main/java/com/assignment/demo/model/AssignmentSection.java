package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assignment_section")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_section_id")
    private Long ID;

    // FK: assignments_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    // FK: questions_material_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_material_id")
    private QuestionMaterial questionMaterial;

    @Column(name = "name")
    private String name;

    @Column(name = "question_type")
    private String questionType;
    public AssignmentSection(Assignment assignment, QuestionMaterial questionMaterial, String name, String questionType) {
        this.assignment = assignment;
        this.questionMaterial = questionMaterial;
        this.name = name;
        this.questionType = questionType;
    }

}
