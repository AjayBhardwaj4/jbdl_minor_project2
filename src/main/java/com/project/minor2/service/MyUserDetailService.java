package com.project.minor2.service;

import com.project.minor2.model.MyUser;
import com.project.minor2.model.request.UserCreateRequest;
import com.project.minor2.repository.MyUserCacheRepository;
import com.project.minor2.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserCacheRepository.get(username);
        if(user == null) {
            user = myUserRepository.findByUsername(username);
            if(user != null) {
                myUserCacheRepository.set(user); //This call can be made parallel
            }
        }

        return user;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest) {
        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null) {
            myUserBuilder.authority(studentAuthority);
        } else {
            myUserBuilder.authority(adminAuthority);
        }

        return myUserRepository.save(myUserBuilder.build());
    }
}
