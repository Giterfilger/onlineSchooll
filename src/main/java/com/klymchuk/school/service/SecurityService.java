package com.klymchuk.school.service;

import com.klymchuk.school.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityService {

    private final StudentService userService;

    public User getUserByEmail(String email) {
        return userService.getStudentByEmail(email);
    }

}
