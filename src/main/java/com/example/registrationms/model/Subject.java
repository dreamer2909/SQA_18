package com.example.registrationms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Subject {
    @Id
    private String code;
    @Column(nullable = false)
    private String name;
    private int numberCredit;
    @OneToMany
    private List<Course> courses = new ArrayList<>();
}
