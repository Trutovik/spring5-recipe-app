package com.sabal.spring5recipeapp.services.impl;

import com.sabal.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.sabal.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.sabal.spring5recipeapp.domain.Recipe;
import com.sabal.spring5recipeapp.exceptions.NotFoundException;
import com.sabal.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  RecipeCommandToRecipe recipeCommandToRecipe;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull(recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  void getRecipes() {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipeData = new HashSet<>();
    recipeData.add(recipe);
    when(recipeRepository.findAll()).thenReturn(recipeData);
    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  public void testDeleteById() throws Exception {
    long idToDelete = Long.valueOf(2L);
    recipeService.deleteById(idToDelete);

    verify(recipeRepository, times(1)).deleteById(anyLong());
  }

  @Test
  public void getRecipeByIdTestNotFound() throws Exception {
    Assertions.assertThrows(NotFoundException.class, () -> {
      Optional<Recipe> recipeOptional = Optional.empty();
      when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
      Recipe recipeReturned = recipeService.findById(1L);
    });
  }

}