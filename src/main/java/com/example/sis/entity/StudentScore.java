package com.example.sis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_score_t")
@Data
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_score_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Double score1;
    private Double score2;

    // Tính điểm tổng kết
    public Double getFinalScore() {
        if (score1 == null || score2 == null) return 0.0;
        return 0.3 * score1 + 0.7 * score2;
    }

    // Xếp loại Grade
    public String getLetterGrade() {
        double finalScore = getFinalScore();
        if (finalScore >= 8.0) return "A";
        if (finalScore >= 6.0) return "B";
        if (finalScore >= 4.0) return "D";
        return "F";
    }
}