package com.assignment.demo.repository;
import org.springframework.data.domain.Pageable;
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
            (sr.id, st.ID, c.classCode, a.title, a.description, a.assignmentType,sv.score, sv.review, sv.modifiedAt, sr.gradingStatus) 
            FROM ScoreReport sr 
            JOIN sr.student st 
            JOIN sr.student.classes c
            JOIN sr.assignment a 
            JOIN ScoreVersion sv ON sv.scoreReport.id = sr.id 
            WHERE sr.id = :scoreReportId 
            ORDER BY sv.modifiedAt DESC
            """)
    List<AssignmentDetailDTO> findAssignmentDetail(Long scoreReportId, Pageable pageable);
    @Query("""
            SELECT new com.assignment.demo.dto.AssignmentBasicDTO
            (sr.id, st.ID, c.classCode, a.title, a.assignmentType,sv.score, sv.review, sv.modifiedAt, sr.gradingStatus) 
            FROM ScoreReport sr 
            JOIN sr.student st 
            JOIN sr.student.classes c
            JOIN sr.assignment a 
            JOIN ScoreVersion sv ON sv.scoreReport.id = sr.id 
            
            """)
    List<AssignmentBasicDTO> findAllAssignmentDetail();
    @Query(value = """
       SELECT sr.score_report_id, st.student_id, a.title, a.description, a.assignment_type,
              sv.score, sv.review, sv.modified_at, sr.grading_status
       FROM score_report sr
       JOIN student st ON st.student_id = sr.student_id
       JOIN assignment a ON a.assignment_id = sr.assignment_id
       JOIN score_version sv ON sv.score_report_id = sr.score_report_id
       WHERE sr.score_report_id = :scoreReportId
       ORDER BY sv.modified_at DESC
       LIMIT 1
       """, nativeQuery = true)
    Object[] findLatestNative(Long scoreReportId);

}
