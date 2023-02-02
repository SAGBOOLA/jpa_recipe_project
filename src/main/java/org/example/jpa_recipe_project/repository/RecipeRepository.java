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

    @Query("select r from Recipe r where r.recipeName like concat('%', :name, '%') ")
    List<Recipe> findRecipeNameContain(@Param("name") String name);

    @Query("select r from Recipe r, RecipeIngredient ri, Ingredient i " +
            "where i = ri.ingredient and ri member of r.recipeIngredients and i = :name")
    List<Recipe> selectRecipesByIngredientName(@Param("name") String ingredientName);

    @Query("select r from Recipe r inner join r.categories c where c.category = :name")
    List<Recipe> selectByCategory(@Param("name") String category);

    @Query("select r from Recipe r inner join r.categories c where c.category in :names")
    List<Recipe> selectAllByRecipeThatMatchOneOrMoreCategories(@Param("names") List<String> categories);


}
