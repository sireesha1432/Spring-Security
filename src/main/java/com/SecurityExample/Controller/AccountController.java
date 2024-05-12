package com.SecurityExample.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SecurityExample.Model.Student;
import com.SecurityExample.Model.Users;
import com.SecurityExample.Service.myUserService;

@RestController
public class AccountController {
//	@GetMapping("/Welcome")
//	public String SayWelcom() {
//		return "Hello, Welcome to My Application ";
//	}

	@Autowired
	private myUserService myUserService;

	@Autowired
	private AuthenticationManager authenticationManger;

	List<Student> students = new ArrayList<>(
			List.of(new Student(1, "rajesh", "Rajesh@gamiil.com"), new Student(2, "Ravi", "Ravi@gmail.com")));

	@GetMapping("/Account")
	public String getAccountDetails() {
		return "User, Account Details";
	}

	@GetMapping("/Users")
	public List<Student> getUserDetails() {
		return students;
	}

	@GetMapping("/Contact")
	public String contactUs() {
		return "9390049020";
	}

	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody Users user) {
		Users user1 = myUserService.Save(user);
		return ResponseEntity.ok(user1);

	}

	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		Authentication authentication = authenticationManger
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		return "succuss";

	}

}
