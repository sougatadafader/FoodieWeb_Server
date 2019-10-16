package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.Recipe;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.User;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.RecipeRepository;
import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories.UserRepository;


@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/recipe")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
		if(recipeRepository.findRecipeByStringId(recipe.getRecipeId())==null){
			return recipeRepository.save(recipe);
		}
		return null;
        
	}
	
	@GetMapping("/api/recipes")
	public List<Recipe> findAllRecipes() {
		return (List<Recipe>) recipeRepository.findAll();
	}
	
	@GetMapping("/api/recipe/{rId}")
	public Optional<Recipe> findRecipeById(@PathVariable("rId") int rId) {
		return recipeRepository.findById(rId);
	}
	
	@GetMapping("/api/recipe/string/{rId}")
	public Recipe findRecipeByStringId( @PathVariable("rId") String rId) {
		return recipeRepository.findRecipeByStringId(rId);
	}
	
	@PostMapping("/api/recipe/{rId}/user/{uId}")
    public Recipe addRecipeToFavorite(@RequestBody Recipe recipe,
            @PathVariable("rId") String rId,
            @PathVariable("uId") int uId) {
        Recipe recipeFind = recipeRepository.findRecipeByStringId(rId);
        User user = userRepository.findById(uId).get();
        recipeFind.addToFavoriteByUser(user);
        return recipeRepository.save(recipeFind);
     }
	
	@GetMapping("/api/recipe/{rId}/user/{uId}")
	public boolean isAddedToFavoriteByUser(@PathVariable("rId") String rId,
                                           @PathVariable("uId") int uId){
		User user = userRepository.findById(uId).get();
		List<Recipe>favoriteRecipes = user.getFavoriteRecipes();
		for(Recipe recipe:favoriteRecipes) {
			if(recipe.getRecipeId().equals(rId)) {
				return true;
			}
		}
		return false;
	}
	
	@GetMapping("/api/user/{uId}/recipe")
	public List<Recipe> findFavoriteRecipesByUserId(@PathVariable("uId") int uId) {
		User user = userRepository.findById(uId).get();
		return user.getFavoriteRecipes();
	}
}
