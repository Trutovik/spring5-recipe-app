package com.sabal.spring5recipeapp.services.impl;

import com.sabal.spring5recipeapp.commands.RecipeCommand;
import com.sabal.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.sabal.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.sabal.spring5recipeapp.domain.Recipe;
import com.sabal.spring5recipeapp.exceptions.NotFoundException;
import com.sabal.spring5recipeapp.repositories.RecipeRepository;
import com.sabal.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service!");
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().forEach(recipeSet::add);
    return recipeSet;
  }

  @Override
  public Recipe findById(Long id) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);
    if (!recipeOptional.isPresent()) {
      throw new NotFoundException("Recipe Not Found. For ID value: " + id.toString());
    }
    return recipeOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    log.debug("Saved RecipeId: " + savedRecipe.getId());
    return recipeToRecipeCommand.convert(savedRecipe);
  }

  @Override
  @Transactional
  public RecipeCommand findCommandById(Long l) {
    return recipeToRecipeCommand.convert(findById(l));
  }

  @Override
  public void deleteById(Long idToDelete) {
    recipeRepository.deleteById(idToDelete);
  }
}
