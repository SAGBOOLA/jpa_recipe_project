package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.Recipe;
import org.example.jpa_recipe_project.entity.RecipeCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

    List<Recipe> findByRecipeNameContains(String recipeName);

    List<Recipe> findAllByRecipeIngredients_Ingredient_IngredientNameContainsIgnoreCase(String ingredientName);


    @Query("select r from Recipe r where r.categories =:c")
    List<Recipe> selectByCategory(@Param("c") String category);

    @Query("select r from Recipe r where r.categories = ?1 or r.categories = ?2 ")
    List<Recipe> selectAllByRecipeThatMatchOneOrMoreCategories(@Param("1") String category1, @Param("2") String category2);


}
