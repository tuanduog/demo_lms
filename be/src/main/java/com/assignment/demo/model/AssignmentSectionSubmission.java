package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_section_subission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSectionSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignments_section_submission_id")
    private Long ID;

    // FK: assignments_section_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_section_id")
    private AssignmentSection assignmentSection;

    @Column(name = "section_score")
    private Double sectionScore;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

}
