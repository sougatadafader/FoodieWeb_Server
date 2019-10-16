package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.Comment;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.Recipe;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.User;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.CommentRepository;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.RecipeRepository;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.UserRepository;


@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/api/recipe/{recipeId}/user/{userId}/comment")
	public List<Comment> createComment(
			@PathVariable("userId") int userId,
			@PathVariable("recipeId") String recipeId,
			@RequestBody Comment comment) {
		User user = userRepository.findById(userId).get();
		System.out.println(user.getUsername());
		Recipe recipe = recipeRepository.findRecipeByStringId(recipeId);
		if(recipe!=null) {
			comment.setUser(user);
			comment.setRecipe(recipe);
			List<Comment>comments = recipe.getComments();
			comments.add(comment);
			commentRepository.save(comment);
			List<Comment>commentList=recipe.getComments();
			Collections.reverse(commentList);
			return commentList;
		}
		return null;
	}
	
	@GetMapping("/api/recipe/{recipeId}/comment")
	public List<Comment> getComments(
			@PathVariable("recipeId") String recipeId) {
		List<Comment>commentList=recipeRepository.findRecipeByStringId(recipeId).getComments();
		Collections.reverse(commentList);
		return commentList;
	}
	
	

}
