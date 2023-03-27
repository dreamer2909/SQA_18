package com.example.registrationms.controller;

import com.example.registrationms.model.Subject;
import com.example.registrationms.respository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepo;
    @GetMapping("/all")
    public ResponseEntity<List<Subject>> subjects() {
        return ResponseEntity.ok(subjectRepo.findAll());
    }
}
