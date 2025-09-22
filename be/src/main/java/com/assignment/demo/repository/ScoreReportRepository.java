package com.assignment.demo.repository;

import com.assignment.demo.dto.AssignmentBasicDTO;
import com.assignment.demo.dto.AssignmentDetailDTO;
import com.assignment.demo.model.ScoreReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreReportRepository extends JpaRepository<ScoreReport,Long> {
//    @Query("SELECT new com.assignment.demo.dto.AssignmentDetailDTO( " +
//            "sr.id, st.ID, a.title, a.description, a.assignmentType, " +
//            "sv.score, sv.review, sv.modifiedAt, sr.gradingStatus) " +
//            "FROM ScoreReport sr " +
//            "JOIN sr.student st " +
//            "JOIN sr.assignment a " +
//            "JOIN ScoreVersion sv ON sv.scoreReport.id = sr.id " +
//            "WHERE sr.id = :scoreReportId " +
//            "ORDER BY sv.modifiedAt DESC")
    @Query("""
            SELECT new com.assignment.demo.dto.AssignmentDetailDTO
            (sr.id, st.ID, a.title, a.description, a.assignmentType,sv.score, sv.review, sv.modifiedAt, sr.gradingStatus) 
            FROM ScoreReport sr 
            JOIN sr.student st 
            JOIN sr.assignment a 
            JOIN ScoreVersion sv ON sv.scoreReport.id = sr.id 
            WHERE sr.id = :scoreReportId 
            """)
    AssignmentDetailDTO findAssignmentDetail(Long scoreReportId);
    @Query("""
            SELECT new com.assignment.demo.dto.AssignmentBasicDTO
            (sr.id, st.ID, a.title, a.assignmentType,sv.score, sv.review, sv.modifiedAt, sr.gradingStatus) 
            FROM ScoreReport sr 
            JOIN sr.student st 
            JOIN sr.assignment a 
            JOIN ScoreVersion sv ON sv.scoreReport.id = sr.id 
            
            """)
    List<AssignmentBasicDTO> findAllAssignmentDetail();
}
