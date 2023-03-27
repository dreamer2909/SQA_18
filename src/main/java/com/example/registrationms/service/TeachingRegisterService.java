package com.example.registrationms.service;

import com.example.registrationms.dto.CourseDTO;
import com.example.registrationms.dto.TeachingRegisterRequest;
import com.example.registrationms.exception.DuplicateRegisterException;
import com.example.registrationms.exception.ShiftInputException;
import com.example.registrationms.exception.WeekDayInputException;
import com.example.registrationms.exception.WeekInputException;
import com.example.registrationms.model.Course;
import com.example.registrationms.model.Schedule;
import com.example.registrationms.model.WeekDay;
import com.example.registrationms.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeachingRegisterService {
    private final ScheduleRepository scheduleRepo;
    private final SubjectRepository subjectRepo;
    private final TeacherRepository teacherRepo;
    private final ClassroomRepository classroomRepo;
    private final CourseRepository courseRepo;

    public List<CourseDTO> getAll() {
        return courseRepo.findAll()
                .stream()
                .map(course -> new CourseDTO(
                        course.getTeacher().getCode(),
                        course.getSubject().getCode(),
                        course.getSchedules().stream().map(schedule -> schedule.getId()).collect(Collectors.toList())))
                .toList();
    }
    public void register(TeachingRegisterRequest request) {
        var teacher = teacherRepo.findByTeacherCode(request.teacherCode())
                .orElseThrow();
        var subject = subjectRepo.getReferenceById(request.subjectCode());
        var room = classroomRepo.getReferenceById(request.roomId());

        int week = request.week(), weekDay = request.weekDay(), shift = request.shift();
        if (week < 1 || week > 30) {
            throw new WeekInputException();
        }
        if (weekDay < 2 || weekDay > 7) {
            throw new WeekDayInputException();
        }
        if (shift < 1 || shift > 6) {
            throw new ShiftInputException();
        }

        WeekDay wDay = WeekDay.UNDEFINED;
        switch (weekDay) {
            case 2:
                wDay = WeekDay.MONDAY;
                break;
            case 3:
                wDay = WeekDay.TUESDAY;
                break;
            case 4:
                wDay = WeekDay.WEDNESDAY;
                break;
            case 5:
                wDay = WeekDay.THURSDAY;
                break;
            case 6:
                wDay = WeekDay.FRIDAY;
                break;
            case 7:
                wDay = WeekDay.SATURDAY;
                break;
        }

        var schedule = scheduleRepo.findByShiftAndWeekDayAndWeek(shift, wDay, week);
        if (schedule.isPresent()) throw new DuplicateRegisterException();

        var s = new Schedule(request.week(), request.weekDay(), request.shift());
        s.setRoom(room);

        var course = Course.builder()
                .name("DEMO")
                .subject(subject)
                .teacher(teacher)
                .build();
        course = courseRepo.save(course);

        var schedules = getSchedules(course.getId());
        s = scheduleRepo.save(s);
        schedules.add(s);
        course.setSchedules(schedules);
        courseRepo.save(course);
    }

    public List<Schedule> getSchedules(int courseId) {
        var course = courseRepo.getReferenceById(courseId);
        if (course.getSchedules() == null || course.getSchedules().isEmpty()) course.setSchedules(new ArrayList<>());
        return course.getSchedules();
    }
}
