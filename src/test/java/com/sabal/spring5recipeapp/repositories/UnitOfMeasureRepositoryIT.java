package com.sabal.spring5recipeapp.repositories;

import com.sabal.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Runner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  void setUp() {
  }

  //@Test
  void findByDescription() {
    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
    assertEquals("Teaspoon", uomOptional.get().getDescription());
  }
}