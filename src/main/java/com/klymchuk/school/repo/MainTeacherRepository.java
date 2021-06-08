package com.klymchuk.school.repo;

import com.klymchuk.school.model.MainTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainTeacherRepository extends JpaRepository<MainTeacher, Integer> {
    MainTeacher getTeacherByEmail(String email);
}
