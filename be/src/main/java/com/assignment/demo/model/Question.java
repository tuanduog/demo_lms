package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    // FK tới AssignmentSection
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_section_id", nullable = false)
    private AssignmentSection assignmentSection;

    // FK tới QuestionMaterial
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_material_id", nullable = false)
    private QuestionMaterial questionMaterial;

    @Column(name = "question_content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "question_explain", columnDefinition = "TEXT")
    private String questionExplain;

}
