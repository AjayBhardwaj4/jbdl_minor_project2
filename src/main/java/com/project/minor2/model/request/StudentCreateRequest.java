package com.project.minor2.model.request;

import com.project.minor2.model.Student;
import com.project.minor2.model.StudentAccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    private String address;
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Student toStudent() {
        return Student.builder()
                .name(name)
                .address(address)
                .contact(contact)
                .address(address)
                .email(email)
                .accountStatus(StudentAccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toUser() {
        return UserCreateRequest.builder()
                .student(toStudent())
                .username(username)
                .password(password)
                .build();
    }

}
