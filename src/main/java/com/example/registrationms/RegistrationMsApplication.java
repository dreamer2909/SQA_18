package com.example.registrationms;

import com.example.registrationms.model.Role;
import com.example.registrationms.model.User;
import com.example.registrationms.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class RegistrationMsApplication implements CommandLineRunner {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(RegistrationMsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // add sample data
        var teacher = User.builder()
                .username("MGV001")
                .password(encoder.encode("12345"))
                .dob(LocalDate.of(1990, 10, 10))
                .address("HaNoi")
                .name("Nguyen Van A")
                .role(Role.TEACHER)
                .build();
        System.out.println(userRepo.save(teacher));
    }
}
