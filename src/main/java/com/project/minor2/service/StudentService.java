package com.project.minor2.service;

import com.project.minor2.model.MyUser;
import com.project.minor2.model.Student;
import com.project.minor2.model.request.StudentCreateRequest;
import com.project.minor2.model.request.UserCreateRequest;
import com.project.minor2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailService myUserDetailService;


    public void create(StudentCreateRequest studentCreateRequest) {
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();

        MyUser myUser = myUserDetailService.createUser(userCreateRequest);

        Student student = studentCreateRequest.toStudent();
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(int sId) {
        return studentRepository.findById(sId).orElse(null);
    }
}
