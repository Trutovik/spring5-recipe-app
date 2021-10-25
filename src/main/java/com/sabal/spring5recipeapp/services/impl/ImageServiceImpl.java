package com.sabal.spring5recipeapp.services.impl;

import com.sabal.spring5recipeapp.domain.Recipe;
import com.sabal.spring5recipeapp.repositories.RecipeRepository;
import com.sabal.spring5recipeapp.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

  private final RecipeRepository recipeRepository;

  public ImageServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void saveImageFile(Long recipeId, MultipartFile file) {

    try {
      Recipe recipe = recipeRepository.findById(recipeId).get();

      Byte[] byteObjects = new Byte[file.getBytes().length];
      int i = 0;
      for (Byte byteObject : byteObjects) {
        byteObjects[i++] = byteObject;
      }
      recipe.setImage(byteObjects);
      recipeRepository.save(recipe);

    } catch (IOException e) {

      log.error("error occured: " + e);
      e.printStackTrace();
    }

  }
}
