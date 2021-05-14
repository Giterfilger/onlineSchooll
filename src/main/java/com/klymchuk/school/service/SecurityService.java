package com.klymchuk.school.service;

import com.klymchuk.school.model.User;
import com.klymchuk.school.repo.AdminRepository;
import com.klymchuk.school.repo.MainTeacherRepository;
import com.klymchuk.school.repo.StudentRepository;
import com.klymchuk.school.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityService {

    private final StudentRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final MainTeacherRepository mainTeacherRepository;

    public User getUserByEmail(String email) {
        User user = userRepository.getStudentByEmail(email);
        if (user == null) {
            user = teacherRepository.getTeacherByEmail(email);
        }
        if (user == null) {
            user = adminRepository.getAdminByEmail(email);
        }
        if(user == null){
            user = mainTeacherRepository.getTeacherByEmail(email);
        }
        return user;
    }

}
