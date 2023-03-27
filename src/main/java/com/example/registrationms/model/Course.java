package com.example.registrationms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Course {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private int capacity = 60;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;
}
