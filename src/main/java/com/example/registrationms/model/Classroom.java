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
public class Classroom {
    @Id @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Schedule> schedules = new ArrayList<>();
}
