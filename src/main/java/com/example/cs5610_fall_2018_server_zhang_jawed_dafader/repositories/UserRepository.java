package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.User;


public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("SELECT user from User user WHERE user.username=:username AND user.password=:password")
	public User findUserByCredentials(@Param("username") String username, @Param("password") String password);
	
}
