package com.project.minor2.model.request;

import com.project.minor2.model.Admin;
import com.project.minor2.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;

}
