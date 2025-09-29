package com.assignment.demo.service;

import com.assignment.demo.dto.BlankQuestionDTO;
import com.assignment.demo.model.BlankQuestion;
import com.assignment.demo.repository.BlankQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    private BlankQuestionRepository blankQuestionRepository;

    public List<BlankQuestion> findBlankQuestion (Long questionId) {
        List<BlankQuestion> blankQuestion =  blankQuestionRepository.findByQuestion_QuestionId(questionId);
        return blankQuestion;
    }
}
