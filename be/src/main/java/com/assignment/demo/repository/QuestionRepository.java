package com.assignment.demo.repository;

import com.assignment.demo.dto.QuestionDisplayDTO;
import com.assignment.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query("""
            SELECT new com.assignment.demo.dto.QuestionDisplayDTO(
                q.id,
                sec.id,
                q.type,
                qm.url,
                q.content
            )
            FROM Question q
            JOIN q.assignmentSection sec
            LEFT JOIN q.questionMaterial qm
            WHERE sec.assignment.id = :id
            
            """)
    public List<QuestionDisplayDTO> findAllByAssignment_Id(Long id);

}
