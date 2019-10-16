package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models;



import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@Column(unique=true)
	private String email;
	
	private String aboutMe;
	
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Comment>comments;
	
	@CreationTimestamp 
	private Date created;
	
	@ManyToMany
	@JoinTable(name="FAVORITE",
	joinColumns=@JoinColumn(name="USER_ID", 
	referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name=
	   "RECIPE_ID", referencedColumnName="ID"))
	@JsonIgnore
	private List<Recipe>favoriteRecipes;


	private String userRole;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void set(User newUser) {
		  this.username = newUser.username;
		  this.password = newUser.password;
		  this.email = newUser.email;
		  this.aboutMe = newUser.aboutMe;
		  this.userRole = newUser.userRole;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}
	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
	
	public void addRecipeToFavorite(Recipe recipe) {
        this.favoriteRecipes.add(recipe);
        if(!recipe.getUsers().contains(this)) {
            recipe.getUsers().add(this);
    }}

}
