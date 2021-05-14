package com.klymchuk.school.repo;

import com.klymchuk.school.model.Admin;
import com.klymchuk.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin getAdminByEmail(String email);
}
