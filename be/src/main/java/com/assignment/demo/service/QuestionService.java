package com.assignment.demo.service;

import com.assignment.demo.dto.CorrectAnswerDTO;
import com.assignment.demo.dto.QuestionAnswerDTO;
import com.assignment.demo.dto.QuestionDisplayDTO;
import com.assignment.demo.repository.MCQRepository;
import com.assignment.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    MCQRepository mcqRepository;
    public List<QuestionDisplayDTO> getQuestionListByAssignment_Id(Long id){
        return questionRepository.findAllByAssignment_Id(id);
    }

    public List<CorrectAnswerDTO> findByQuestion_Id(Long id){
        return mcqRepository.findByQuestion_Id(id);
    }
}
