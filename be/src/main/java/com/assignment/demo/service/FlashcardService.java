package com.assignment.demo.service;

import com.assignment.demo.dto.FlashcardDetailDTO;
import com.assignment.demo.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    @Autowired
    FlashcardRepository flashcardRepository;
//    public List<FlashcardDetailDTO> getFlashcardById(Long id){
//
//    }
}
