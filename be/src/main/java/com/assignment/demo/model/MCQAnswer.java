package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mcq_answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcqa_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "is_correct")
    private boolean isCorrect;
}
