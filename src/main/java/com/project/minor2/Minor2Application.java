package com.project.minor2;

import com.project.minor2.model.Admin;
import com.project.minor2.model.MyUser;
import com.project.minor2.repository.AdminRepository;
import com.project.minor2.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Minor2Application implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(Minor2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MyUser myUser = MyUser.builder()
				.username("vishal1234")
				.password(passwordEncoder.encode("Vishal1234"))
				.authority("adm")
				.build();

		myUser = myUserRepository.save(myUser);

		Admin admin = Admin.builder()
				.age(23)
				.name("Vishal Joshi")
				.myUser(myUser)
				.build();

		adminRepository.save(admin);
	}
}
