package com.assignment.demo.controller;

import com.assignment.demo.model.Course;
import com.assignment.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/add-course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) throws  Exception{
        if(course != null){
            Course newCourse = courseService.addCourse(course);
            return ResponseEntity.ok().body(newCourse);
        }
        return null;
    }

    @GetMapping("/get-course-info/{courseId}")
    public ResponseEntity<Course> getCourseInfo(@PathVariable Long courseId) throws Exception{
        if(courseId != null){
            Course course = courseService.findCourseById(courseId);
            return ResponseEntity.ok().body(course);
        }
        return null;
    }
}
