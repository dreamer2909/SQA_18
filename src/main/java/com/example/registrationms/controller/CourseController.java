package com.example.registrationms.controller;

import com.example.registrationms.model.Course;
import com.example.registrationms.respository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepo;
    @GetMapping("/all")
    public ResponseEntity<List<Course>> courses() {
        return ResponseEntity.ok(courseRepo.findAll());
    }
}
