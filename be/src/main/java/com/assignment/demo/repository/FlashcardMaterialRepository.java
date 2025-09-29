package com.assignment.demo.repository;

import com.assignment.demo.model.FlashcardMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardMaterialRepository extends JpaRepository<FlashcardMaterial,Long> {
}
