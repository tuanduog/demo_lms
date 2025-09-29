package com.assignment.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flashcard_materials")
public class FlashcardMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flashcard_material_id")
    private Long flashcardMaterialId;

    @Column(name = "quizlet_id")
    private Long quizletId;

    private String key;
    private String value;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ✅ N - 1 về Flashcard
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
