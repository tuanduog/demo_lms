package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blank_answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlankAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blankanswer_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blankquestion_id")
    private BlankQuestion blankQuestion;

    @Column(name = "answer_content")
    private String answerContent;
}
