package com.assignment.demo.service;

import com.assignment.demo.model.MCQAnswer;
import com.assignment.demo.repository.MCQAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCQAnswerService {
    @Autowired
    private MCQAnswerRepository mcqAnswerRepository;

    public MCQAnswer checkCorrectAns (Long questionId){
        List<MCQAnswer> mcqAns = mcqAnswerRepository.findByQuestion_QuestionId(questionId);
        for(MCQAnswer ma : mcqAns){
            if(ma.isCorrect()){
                return ma;
            }
        }
        return null;
    }
}
