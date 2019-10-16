package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.Admin;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.AdminRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class AdminService {
	
	@Autowired
	AdminRepository userRepository;
	
	@PostMapping("/api/register/admin")
	public Admin register(@RequestBody Admin user,HttpSession session) {
		Admin addedUser = userRepository.save(user);//saves into db
	    return addedUser;
	}
	
	/**
	 * Redundant api
	 */
	@GetMapping("/api/profile/admin")
	public Optional<Admin> profile(HttpSession session) {
		Admin currentUser= (Admin) session.getAttribute("currentUser");
		System.out.println(currentUser);
		return userRepository.findById(currentUser.getId()); 
	}
	
	@PostMapping("/api/login/admin")
	public Admin login(@RequestBody Admin user,
			          HttpSession session) {
		user = (Admin) userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		session.setAttribute("currentUser", user);
	    return user;
	}

}