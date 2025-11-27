package com.example.sis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subject_t")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subject_id;

    @Column(nullable = false)
    private String subject_code;

    @Column(nullable = false)
    private String subject_name;

    private Integer credit;
}