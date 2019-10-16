package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.SystemUser;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.User;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.SystemUserRepository;



@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class SystemUserService {
	
	@Autowired
	SystemUserRepository userRepository;
	
	@PostMapping("/api/register/systemuser")
	public SystemUser register(@RequestBody SystemUser user,HttpSession session) {
		SystemUser currentUser = userRepository.save(user);//saves into db
		session.setAttribute("currentUser", currentUser);
	    return currentUser;
	}
	
	/**
	 * Redundant api
	 */
	@GetMapping("/api/profile/systemuser")
	public Optional<SystemUser> profile(HttpSession session) {
		SystemUser currentUser= (SystemUser) session.getAttribute("currentUser");
		System.out.println(currentUser);
		return userRepository.findById(currentUser.getId()); 
	}
	
	@PostMapping("/api/login/systemuser")
	public SystemUser login(@RequestBody SystemUser user,
			          HttpSession session) {
		user = (SystemUser) userRepository.findUserByCredentials(user.isEnabled(),user.getUsername(), user.getPassword());
		session.setAttribute("currentUser", user);
	    return user;
	}
	
	@PostMapping("/api/logout/systemuser")
	public void logout(HttpSession session) {
		System.out.println("lgo");
		session.invalidate();
	}


}

