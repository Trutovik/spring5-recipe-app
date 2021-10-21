package com.sabal.spring5recipeapp.services;

import com.sabal.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();
}
