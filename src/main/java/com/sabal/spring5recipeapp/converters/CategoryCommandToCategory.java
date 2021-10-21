package com.sabal.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import com.sabal.spring5recipeapp.commands.CategoryCommand;
import com.sabal.spring5recipeapp.domain.Category;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

  @Synchronized
  @Nullable
  @Override
  public Category convert(CategoryCommand categoryCommand) {
    if (categoryCommand == null) {
      return null;
    }

    final Category category = new Category();
    category.setId(categoryCommand.getId());
    category.setDescription(categoryCommand.getDescription());
    return category;
  }
}
