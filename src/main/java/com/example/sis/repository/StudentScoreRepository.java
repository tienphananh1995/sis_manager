package com.example.sis.repository;

import com.example.sis.entity.Student;
import com.example.sis.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
    // Tìm danh sách điểm dựa trên đối tượng Student (Spring Data JPA tự generate query)
    List<StudentScore> findByStudent(Student student);
}