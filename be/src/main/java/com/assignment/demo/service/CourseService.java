package com.assignment.demo.service;

import com.assignment.demo.model.Course;
import com.assignment.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).get();
    }
}
