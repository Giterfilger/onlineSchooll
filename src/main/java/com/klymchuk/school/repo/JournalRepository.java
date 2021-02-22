package com.klymchuk.school.repo;

import com.klymchuk.school.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findByStudentId(int student_id);
    List<Journal> findByStudentIdAndSubjectId(int student_id, int subjectId);
}
