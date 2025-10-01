package com.assignment.demo.service;

import com.assignment.demo.dto.CorrectAnswerDTO;
import com.assignment.demo.repository.MCQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MCQAnswerService {
    @Autowired
    MCQRepository mcqRepository;
    public Map<Long, String> getCorrectAnswerByAssignment_Id(Long id){
        Map<Long,String> map= new HashMap<>();
        System.out.println("assignmentId: "+id);
        List<CorrectAnswerDTO> answers= mcqRepository.findAllByAssignment_Id(id);
        System.out.println("answers:"+ answers);
        for(CorrectAnswerDTO answer: answers){
            map.put(answer.getQuestionId(),answer.getAnswerContent());
        }
        return map;
    }
}
