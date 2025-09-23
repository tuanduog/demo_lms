package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_material_id")
    private QuestionMaterial questionMaterial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_section_id")
    private AssignmentSection assignmentSection;

    @Column(name = "question_content", columnDefinition = "TEXT")
    private String questionContent;

    @Column(name = "type")
    private String type;

    @Column(name = "explaination")
    private String explaination;

    @Column(name = "score")
    private Double score;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MCQAnswer> mcqAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BlankQuestion> blankQuestions = new ArrayList<>();
}
