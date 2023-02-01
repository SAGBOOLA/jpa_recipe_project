package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface IngredientRepository extends CrudRepository<Ingredient, Integer>{

    Optional<Ingredient> findByIngredientName(String ingredientName);

    List<Ingredient> findAllByIngredientNameContains(String ingredientName);
}
