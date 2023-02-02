package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer>{
}
