package com.sabal.spring5recipeapp.services.impl;

import com.sabal.spring5recipeapp.domain.Recipe;
import com.sabal.spring5recipeapp.repositories.RecipeRepository;
import com.sabal.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service!");
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().forEach(recipeSet::add);
    return recipeSet;
  }
}
