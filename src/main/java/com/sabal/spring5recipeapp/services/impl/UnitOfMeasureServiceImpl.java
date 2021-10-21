package com.sabal.spring5recipeapp.services.impl;

import com.sabal.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.sabal.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sabal.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.sabal.spring5recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Override
  public Set<UnitOfMeasureCommand> listAllUoms() {

    return StreamSupport.stream(unitOfMeasureRepository.findAll()
            .spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        .collect(Collectors.toSet());
  }
}
