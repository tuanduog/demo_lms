package com.assignment.demo.repository;

import com.assignment.demo.dto.CorrectAnswerDTO;
import com.assignment.demo.model.MCQAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MCQRepository extends JpaRepository<MCQAnswer,Long> {
    public List<String> findAllAnswerContentByQuestion_Id(Long id);

    @Query("""
             SELECT new com.assignment.demo.dto.CorrectAnswerDTO
            (q.id, ma.answerContent) 
            FROM MCQAnswer ma
            JOIN ma.question q
            WHERE q.id=:id
            """)
    public List<CorrectAnswerDTO> findByQuestion_Id(Long id);


    @Query("""
             SELECT new com.assignment.demo.dto.CorrectAnswerDTO(
                     q.id, ma.answerContent
                 )
                 FROM MCQAnswer ma
                 JOIN ma.question q
                 JOIN q.assignmentSection sec
                 LEFT JOIN sec.assignment a
                 WHERE ma.isCorrect = TRUE
                   AND a.id = :id
            """)
    public List<CorrectAnswerDTO> findAllByAssignment_Id(Long id);
}
