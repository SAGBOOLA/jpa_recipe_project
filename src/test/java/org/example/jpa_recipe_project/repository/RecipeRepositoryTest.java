package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest

public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;
    

    Recipe createdRecipe1;
    Recipe createdRecipe2;

    Ingredient ingredient1;
    List<RecipeCategory> categories;


    @BeforeEach
    public void setup(){
        ingredient1 = new Ingredient("Almond milk");
        Ingredient ingredient2 = new Ingredient("Salt");


        List<RecipeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(new RecipeIngredient(ingredient1, 30, Measurement.G));
        ingredientList.add(new RecipeIngredient(ingredient2, 5, Measurement.G));

        RecipeIngredient recipeIngredient1 = new RecipeIngredient(ingredient1, 30, Measurement.G);
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(ingredient2, 5, Measurement.G);

        RecipeInstruction recipeInstruction1 = new RecipeInstruction("How to cook");
        RecipeInstruction recipeInstruction2 = new RecipeInstruction("Add a little");

        categories = Arrays.asList(
                new RecipeCategory("Vegan"),
        new RecipeCategory("Chicken")
        );

        List<RecipeCategory> categories1 = Arrays.asList(
                new RecipeCategory("Chicken")
        );
        RecipeCategory category1 = new RecipeCategory("Vegan");
        RecipeCategory category2 = new RecipeCategory("Chicken");

        /*List<Recipe> recipeList = Arrays.asList(
                new Recipe("Noodles", ingredientList, recipeInstruction1, categories),
                new Recipe("soup", ingredientList, recipeInstruction2, categories1)
        );*/

        Recipe recipeData1 = new Recipe("Noodles", recipeInstruction1);
        Recipe recipeData2 = new Recipe("soup", recipeInstruction2);
        Recipe recipeData3 = new Recipe("cereal");
        recipeData1.addCategory(category1);
        recipeData1.addRecipeIngredient(recipeIngredient1);

       createdRecipe1 = recipeRepository.save(recipeData1);
       createdRecipe2 = recipeRepository.save(recipeData2);

       assertNotNull(createdRecipe1);
        assertNotNull(createdRecipe1);
    }

    @Test
    public void test_recipeNameContains(){
        String name = "Noodle";
        List<Recipe> actual = recipeRepository.findByRecipeNameContains(name);
        assertNotNull(actual);
        assertEquals(1,actual.size());
        //String expected = createdRecipe1.getRecipeName();
       /*List<Recipe> temp = new ArrayList<>();
       temp.add(new Recipe("Noodles"));
        temp.add(new Recipe("Pasta"));
       List<Recipe> actual = recipeRepository.findByRecipeNameContains(temp.toString());
       assertAll(()->{assertEquals("Noodles", actual.get);});*/


        /*List<Recipe> actual = recipeRepository.findAllByRecipeNameContains(createdRecipe1.getRecipeName());
        St expected = createdRecipe1.getRecipeName();*/

    }

    //@Test
    /*public void test_ingredientName(){
        //String name = "salt";
        List<Recipe> actual = recipeRepository.findAllByRecipeIngredients_Ingredient_IngredientNameContainsIgnoreCase(ingredient1.getIngredientName());
        assertEquals(1, actual.size());
    }*/

    @Test
    public void test_categoryName(){
        List<Recipe> actual = recipeRepository.selectByCategory("Vegan");
        assertEquals(1, actual.size());
    }
}