package com.example.registrationms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Course {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private int capacity;
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Classroom> classrooms = new ArrayList<>();
}
