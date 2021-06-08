package com.klymchuk.school.repo;

import com.klymchuk.school.model.Journal;
import com.klymchuk.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findByStudentId(int student_id);
    List<Journal> findByStudentIdAndSubjectId(int studentId, int subjectId);
    List<Journal> findByStudentIdIn(List<Integer> studentIdList);
}
