package com.assignment.demo.service;

import com.assignment.demo.dto.*;
import com.assignment.demo.model.AssignmentSection;
import com.assignment.demo.model.Question;
import com.assignment.demo.model.QuestionMaterial;
import com.assignment.demo.repository.AssignmentSectionRepository;
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
    private final AssignmentSectionRepository assignmentSectionRepository;

    public QuestionService(QuestionMaterialRepository questionMaterialRepository,
                           QuestionRepository questionRepository,
                           AssignmentSectionRepository assignmentSectionRepository) {
        this.questionMaterialRepository = questionMaterialRepository;
        this.questionRepository = questionRepository;
        this.assignmentSectionRepository = assignmentSectionRepository;
    }

    public List<AssignmentSectionDTO> getQuestionStruct(Long asignId) {
        List<AssignmentSectionDTO> result = new ArrayList<>();

        // lấy tất cả section
        List<AssignmentSection> assignmentSections = assignmentSectionRepository.findAllByAssignment_Id(asignId);

        for (AssignmentSection as : assignmentSections) {
            Long assignmentSectionId = as.getID();

            // lấy materials thuộc section
            List<QuestionMaterial> materials = questionMaterialRepository.findAllByAssignmentSection_ID(assignmentSectionId);

            List<QuestionMaterialDTO> materialDtos = materials.stream().map(m -> {
                List<QuestionDTO> questionDtos = m.getQuestions().stream().map(this::mapQuestionToDto).toList();

                return new QuestionMaterialDTO(
                        m.getID(),
                        m.getUrl(),
                        m.getType(),
                        m.getQuantity(),
                        questionDtos
                );
            }).toList();

            // build sectionDTO
            AssignmentSectionDTO sectionDto = new AssignmentSectionDTO(
                    as.getID(),
                    as.getName(),
                    materialDtos
            );

            result.add(sectionDto);
        }

        // ===== xử lý questions không có material =====
//        List<Question> noMaterialQuestions = questionRepository.findAll()
//                .stream()
//                .filter(q -> q.getQuestionMaterial() == null)
//                .toList();
//
//        if (!noMaterialQuestions.isEmpty()) {
//            List<QuestionDTO> questionDtos = noMaterialQuestions.stream()
//                    .map(this::mapQuestionToDto)
//                    .toList();
//
//            QuestionMaterialDTO noMaterial = new QuestionMaterialDTO(
//                    0L,
//                    "NO_MATERIAL",
//                    null,
//                    questionDtos.size(),
//                    questionDtos
//            );
//
//            // cho "NO_MATERIAL" vào 1 section đặc biệt
//            AssignmentSectionDTO dummySection = new AssignmentSectionDTO(
//                    -1L,
//                    "NO_SECTION",
//                    List.of(noMaterial)
//            );
//            result.add(dummySection);
//        }

        return result;
    }

    public Boolean checkType(Long asignId) {
        boolean result = true;
        // lấy tất cả section
        List<AssignmentSection> assignmentSections = assignmentSectionRepository.findAllByAssignment_Id(asignId);

        for (AssignmentSection as : assignmentSections) {
            Long assignmentSectionId = as.getID();

            // lấy materials thuộc section
            List<QuestionMaterial> materials = questionMaterialRepository.findAllByAssignmentSection_ID(assignmentSectionId);
            for(QuestionMaterial m : materials){
                List<Question> q = m.getQuestions();
                for(Question q1 : q){
                    if(q1.getType().equalsIgnoreCase("Essay")){
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    // helper map Question -> QuestionDTO
    private QuestionDTO mapQuestionToDto(Question q) {
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
    }
}


