package com.klymchuk.school.repo;

import com.klymchuk.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getStudentByEmail(String email);
    List<Student> getStudentByClazzId(int clazzId);
}
