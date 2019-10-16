package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String recipeId;
	
	
	private String recipeName;

	private String creator;
	
	private String image;
	
	@OneToMany(mappedBy="recipe")
	private List<Comment>comments;
	
	@ManyToMany(mappedBy="favoriteRecipes")
	@JsonIgnore
	private List<User> usersWhoLiked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	public List<User> getUsers() {
		return usersWhoLiked;
	}

	public void setUsers(List<User> users) {
		this.usersWhoLiked = users;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}

	public void setUsersWhoLiked(List<User> usersWhoLiked) {
		this.usersWhoLiked = usersWhoLiked;
	}

	
	public void addToFavoriteByUser(User user) {
        this.usersWhoLiked.add(user);
        System.out.println(this);
        if(!user.getFavoriteRecipes().contains(this)) {
        	System.out.println("In model "+user.getUsername());
            user.getFavoriteRecipes().add(this);
   }
	}


}
