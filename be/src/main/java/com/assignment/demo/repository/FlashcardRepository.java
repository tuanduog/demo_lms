package com.assignment.demo.repository;

import com.assignment.demo.dto.FlashcardDetailDTO;
import com.assignment.demo.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard,Long> {
    @Query("""
            SELECT new com.assignment.demo.dto.FlashcardDetailDTO
            (f.id,m.key,m.value) 
            FROM Flashcard f 
            JOIN f.materials m
            WHERE f.id= :id
            """)
    List<FlashcardDetailDTO> findAllByFlashcard_Id( Long id);
}
