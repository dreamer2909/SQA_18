package com.example.registrationms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Teacher extends User {
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    private String address;

    @OneToMany
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany
    private List<Course> courses = new ArrayList<>();
}
