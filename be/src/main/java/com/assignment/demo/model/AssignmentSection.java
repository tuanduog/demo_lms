package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assignment_section")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignments_section_id")
    private Long ID;

    // FK: assignments_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignments_id")
    private Assignment assignment;

    @Column(name = "name")
    private String name;

//    @Column(name = "type")
//    private String type;
}
