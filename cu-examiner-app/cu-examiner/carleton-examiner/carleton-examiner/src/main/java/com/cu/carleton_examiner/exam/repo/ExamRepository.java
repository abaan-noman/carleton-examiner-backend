package com.cu.carleton_examiner.exam.repo;

import com.cu.carleton_examiner.exam.model.Exam;
import com.cu.carleton_examiner.exam.model.ExamKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamKey> {
    Optional<Exam> findByDepartmentAndCourse(String department, String course);

    void deleteByDepartmentAndCourse(String department, String course);
}
