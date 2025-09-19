package com.assignment.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignment_section")
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

    public AssignmentSection() {
    }

    public AssignmentSection(Assignment assignment, QuestionMaterial questionMaterial, String name, String questionType) {
        this.assignment = assignment;
        this.questionMaterial = questionMaterial;
        this.name = name;
        this.questionType = questionType;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public QuestionMaterial getQuestionMaterial() {
        return questionMaterial;
    }

    public void setQuestionMaterial(QuestionMaterial questionMaterial) {
        this.questionMaterial = questionMaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
