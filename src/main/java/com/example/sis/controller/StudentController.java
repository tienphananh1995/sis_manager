package com.example.sis.controller;

import com.example.sis.entity.*;
import com.example.sis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired private StudentRepository studentRepo;
    @Autowired private SubjectRepository subjectRepo;
    @Autowired private StudentScoreRepository scoreRepo;

    @GetMapping("/")
    public String index(Model model) {
        // Gửi cả danh sách điểm VÀ danh sách học sinh ra trang chủ
        model.addAttribute("scores", scoreRepo.findAll());
        model.addAttribute("students", studentRepo.findAll());
        return "index";
    }

    // --- STUDENT ---

    @GetMapping("/student/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/";
    }

    // Chức năng Xoá Học Sinh
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            // Bước 1: Xoá tất cả điểm của học sinh này trước (để tránh lỗi khoá ngoại)
            List<StudentScore> relatedScores = scoreRepo.findByStudent(student);
            scoreRepo.deleteAll(relatedScores);

            // Bước 2: Xoá học sinh
            studentRepo.delete(student);
        }
        return "redirect:/";
    }

    // --- SCORE ---

    @GetMapping("/score/add")
    public String showAddScoreForm(Model model) {
        model.addAttribute("scoreEntry", new StudentScore());
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("subjects", subjectRepo.findAll());
        return "add-score";
    }

    // Chức năng Sửa Điểm (Tái sử dụng form add-score)
    @GetMapping("/score/edit/{id}")
    public String showEditScoreForm(@PathVariable("id") Integer id, Model model) {
        StudentScore existingScore = scoreRepo.findById(id).orElse(null);
        // Gửi thông tin điểm cũ sang form
        model.addAttribute("scoreEntry", existingScore);
        // Gửi danh sách để hiển thị dropdown
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("subjects", subjectRepo.findAll());
        return "add-score";
    }

    @PostMapping("/score/save")
    public String saveScore(@ModelAttribute StudentScore scoreEntry) {
        // JPA tự hiểu: Nếu có ID -> Update. Nếu không có ID -> Insert.
        scoreRepo.save(scoreEntry);
        return "redirect:/";
    }

    // Chức năng Xoá Điểm (Nếu muốn thêm nút xoá điểm riêng)
    @GetMapping("/score/delete/{id}")
    public String deleteScore(@PathVariable("id") Integer id) {
        scoreRepo.deleteById(id);
        return "redirect:/";
    }
}