package com.example.sis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_t")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    @Column(nullable = false)
    private String student_code;

    @Column(nullable = false)
    private String full_name;

    private String address;
}