package com.example.registrationms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Schedule {
    @Id @GeneratedValue
    private Integer id;
    private WeekDay weekDay;
    private int shift;
    private int week;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom room;

    public Schedule(int week, int weekDay, int shift) {
        this.week = week;
        switch (weekDay) {
            case 2:
                this.weekDay = WeekDay.MONDAY;
                break;
            case 3:
                this.weekDay = WeekDay.TUESDAY;
                break;
            case 4:
                this.weekDay = WeekDay.WEDNESDAY;
                break;
            case 5:
                this.weekDay = WeekDay.THURSDAY;
                break;
            case 6:
                this.weekDay = WeekDay.FRIDAY;
                break;
            case 7:
                this.weekDay = WeekDay.SATURDAY;
                break;
            default:
                this.weekDay = WeekDay.UNDEFINED;
        }
        this.shift = shift;
    }
}
