package com.SecurityExample.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecurityExample.Model.Student;

@RestController
public class AccountController {
//	@GetMapping("/Welcome")
//	public String SayWelcom() {
//		return "Hello, Welcome to My Application ";
//	}
	
	List<Student> students = new ArrayList<>(List.of(
			new Student(1,"rajesh","Rajesh@gamiil.com"),
			new Student(2,"Ravi","Ravi@gmail.com")));
	
	
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
		return  "9390049020";
	}

}
