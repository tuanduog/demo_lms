package com.assignment.demo.repository;

import com.assignment.demo.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classes,Integer> {
}
