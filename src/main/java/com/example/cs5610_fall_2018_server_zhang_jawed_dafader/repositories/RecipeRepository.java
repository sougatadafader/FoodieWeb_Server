package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

	@Query("SELECT recipe from Recipe recipe WHERE recipe.recipeId=:id")
	public Recipe findRecipeByStringId(@Param("id") String id);
}
