package com.assignment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlashcardDetailDTO {
    private Long flashcardId;
    private String key;
    private String value;
}
