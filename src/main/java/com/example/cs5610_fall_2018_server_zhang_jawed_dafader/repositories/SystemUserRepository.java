package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.SystemUser;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.User;


public interface SystemUserRepository extends CrudRepository<SystemUser, Integer>{

	@Query("SELECT user from SystemUser user WHERE user.enabled=:enabled AND user.username=:username AND user.password=:password")
	public User findUserByCredentials(@Param("enabled") boolean enabled, @Param("username") String username, @Param("password") String password);
	
}
