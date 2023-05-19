package com.project.minor2.controller;

import com.project.minor2.model.MyUser;
import com.project.minor2.model.Student;
import com.project.minor2.model.request.StudentCreateRequest;
import com.project.minor2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        studentService.create(studentCreateRequest);
    }

    @GetMapping("/student")
    public Student getStudent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getStudent() == null) {
            throw new Exception("User requesting the details is not a student!");
        }

        int studentId = myUser.getStudent().getId();
        return studentService.findStudentByStudentId(studentId);

    }

    @GetMapping("/student_for_admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getAdmin() == null) { //Can be checked via auth.getAuthorities() also
            throw new Exception("User requesting the details is not an admin");
        }

        return studentService.findStudentByStudentId(studentId);
    }
}
