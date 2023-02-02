package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.RecipeInstruction;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, Integer>{
}
