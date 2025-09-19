package com.assignment.demo.repository;

import com.assignment.demo.model.ScoreVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreVersionRepository  extends JpaRepository<ScoreVersion,Integer> {
}
