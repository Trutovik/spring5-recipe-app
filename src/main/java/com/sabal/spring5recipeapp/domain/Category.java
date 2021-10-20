package com.sabal.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "categories")
  private Set<Recipe> recipes;

}


