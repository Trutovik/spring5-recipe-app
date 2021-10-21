package com.sabal.spring5recipeapp.services;

import com.sabal.spring5recipeapp.commands.RecipeCommand;
import com.sabal.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);

  RecipeCommand findCommandById(Long l);

  RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

  void deleteById(Long idToDelete);
}
