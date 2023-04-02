package com.example.registrationms;

import com.example.registrationms.model.*;
import com.example.registrationms.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
public class RegistrationMsApplication implements CommandLineRunner {
    private final UserRepository userRepo;
    private final TeacherRepository teacherRepo;
    private final SubjectRepository subjectRepo;
    private final ClassroomRepository classroomRepo;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(RegistrationMsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // add sample data
        var class1 = Classroom.builder()
                .name("305-A2")
                .build();

        var teacher = Teacher.builder()
                .dob(LocalDate.of(1990, 10, 10))
                .address("HaNoi")
                .build();
        teacher.setCode("MGV001");
        teacher.setName("Nguyen Van A");
        teacher.setUsername("MGV001");
        teacher.setPassword(encoder.encode("12345"));
        teacher.setRole(Role.TEACHER);

        var subject = Subject.builder()
                .code("SQA")
                .name("Quản lý chất lượng phần mềm")
                .build();

        var subject2 = Subject.builder()
                .code("OOP")
                .name("Lap trinh huong doi tuong")
                .build();

        subjectRepo.save(subject);
        subjectRepo.save(subject2);
        teacherRepo.save(teacher);
        classroomRepo.save(class1);
    }
}
