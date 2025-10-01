package com.assignment.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_version")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_version_id")
    private Long courseVersionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "version_no")
    private String versionNo;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "max_received_ticket")
    private Integer maxReceivedTicket;

    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

}