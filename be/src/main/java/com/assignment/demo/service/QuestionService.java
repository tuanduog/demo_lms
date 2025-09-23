package com.assignment.demo.service;

import com.assignment.demo.dto.*;
import com.assignment.demo.model.Question;
import com.assignment.demo.model.QuestionMaterial;
import com.assignment.demo.repository.QuestionMaterialRepository;
import com.assignment.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionMaterialRepository questionMaterialRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionMaterialRepository questionMaterialRepository,
                           QuestionRepository questionRepository) {
        this.questionMaterialRepository = questionMaterialRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuestionMaterialDTO> getQuestionStruct() {
        List<QuestionMaterialDTO> result = new ArrayList<>();

        // llấy materials có câu hỏi
        List<QuestionMaterial> materials = questionMaterialRepository.findAll();
        for (QuestionMaterial m : materials) {
            List<QuestionDTO> questionDtos = m.getQuestions().stream().map(q -> {
                List<MCQAnswerDTO> mcqAnswers = q.getMcqAnswers().stream()
                        .map(a -> new MCQAnswerDTO(a.getId(), a.getAnswerContent(), a.isCorrect()))
                        .toList();

                List<BlankQuestionDTO> blankQuestions = q.getBlankQuestions().stream()
                        .map(bq -> new BlankQuestionDTO(
                                bq.getId(),
                                bq.getBlankSlot(),
                                bq.getBlankAnswers().stream()
                                        .map(ba -> new BlankAnswerDTO(ba.getId(), ba.getAnswerContent()))
                                        .toList()
                        ))
                        .toList();

                return new QuestionDTO(
                        q.getQuestionId(),
                        q.getType(),
                        q.getQuestionContent(),
                        q.getExplaination(),
                        q.getScore(),
                        blankQuestions,
                        mcqAnswers
                );
            }).toList();

            result.add(new QuestionMaterialDTO(
                    m.getID(),
                    m.getType(),
                    m.getUrl(),
                    m.getQuantity(),
                    questionDtos
            ));
        }

        // lấy questions không có material
        List<Question> noMaterialQuestions = questionRepository.findAll()
                .stream()
                .filter(q -> q.getQuestionMaterial() == null) // chỉ lấy những câu không gắn material
                .toList();

        if (!noMaterialQuestions.isEmpty()) {
            List<QuestionDTO> questionDtos = noMaterialQuestions.stream().map(q -> {
                List<MCQAnswerDTO> mcqAnswers = q.getMcqAnswers().stream()
                        .map(a -> new MCQAnswerDTO(a.getId(), a.getAnswerContent(), a.isCorrect()))
                        .toList();

                List<BlankQuestionDTO> blankQuestions = q.getBlankQuestions().stream()
                        .map(bq -> new BlankQuestionDTO(
                                bq.getId(),
                                bq.getBlankSlot(),
                                bq.getBlankAnswers().stream()
                                        .map(ba -> new BlankAnswerDTO(ba.getId(), ba.getAnswerContent()))
                                        .toList()
                        ))
                        .toList();

                return new QuestionDTO(
                        q.getQuestionId(),
                        q.getType(),
                        q.getQuestionContent(),
                        q.getExplaination(),
                        q.getScore(),
                        blankQuestions,
                        mcqAnswers
                );
            }).toList();


            result.add(new QuestionMaterialDTO(
                    0L,
                    "NO_MATERIAL",
                    null,
                    questionDtos.size(),
                    questionDtos
            ));
        }

        return result;
    }

}

