package com.assignment.demo.repository;

import com.assignment.demo.dto.FlashcardDetailDTO;
import com.assignment.demo.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard,Long> {
//    List<FlashcardDetailDTO> get
}
