package com.assignment.demo.controller;

import com.assignment.demo.dto.FlashcardDetailDTO;
import com.assignment.demo.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flashcard")
public class FlashcardController {
    @Autowired
    FlashcardService flashcardService;
    @GetMapping("/{id}")
    public List<FlashcardDetailDTO> getAllFlashcard(@PathVariable Long id){
        return flashcardService.getFlashcardById(id);
    }
}
