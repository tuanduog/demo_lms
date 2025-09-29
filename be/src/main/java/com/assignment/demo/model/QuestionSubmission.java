package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_submission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_submission_id")
    private Long ID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_report_id")
    private ScoreReport scoreReport;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "assignments_section_id")
//    private AssignmentSection assignmentSection;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "student_answer")
    private String studentAnswer;

    @Column(name = "score")
    private Double score;

    @Column(name = "correct_ans")
    private String correctAnswer;

    @Column(name = "explaination")
    private String explaination;

}
