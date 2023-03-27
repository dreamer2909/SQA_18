package com.example.registrationms.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lesson {
    @Id @GeneratedValue
    private Integer id;
    private WeekDay weekDay;
    private int shift;

    private int week;

    @OneToOne
    @JoinColumn(name = "subject_code", referencedColumnName = "subjectCode")
    private Subject subject;

    public Lesson(int week, int weekDay, int shift) {
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
