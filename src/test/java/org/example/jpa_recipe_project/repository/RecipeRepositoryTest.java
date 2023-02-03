package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest

public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;
    Recipe createdRecipe1;
    Recipe createdRecipe2;
    Ingredient almondMilk;


    @BeforeEach
    public void setup(){
        /*RecipeIngredient recipeIngredient2 = new RecipeIngredient(ingredient2, 5, Measurement.G);
        recipeIngredientRepository.save(recipeIngredient1);

        List<RecipeIngredient> ingredientList = Arrays.asList(
                new RecipeIngredient(ingredient1, 30, Measurement.G),
                new RecipeIngredient(ingredient2, 5, Measurement.G)
        );
        List<RecipeCategory> categories = Arrays.asList(
                new RecipeCategory("Vegan"),
                new RecipeCategory("Chicken")
        );

        List<RecipeCategory> categories1 = Arrays.asList(
                new RecipeCategory("Chicken")
        );
        List<Recipe> recipeList = Arrays.asList(
                new Recipe("Noodles", ingredientList, recipeInstruction1, categories),
                new Recipe("soup", ingredientList, recipeInstruction2, categories1)
        );*/


        almondMilk = new Ingredient("Almond milk");
        Ingredient salt = new Ingredient("Salt");
        ingredientRepository.save(almondMilk);

        RecipeIngredient recipeIngredient1 = new RecipeIngredient(almondMilk, 30, Measurement.G);

        RecipeInstruction recipeInstruction1 = new RecipeInstruction("How to cook");
        RecipeInstruction recipeInstruction2 = new RecipeInstruction("Add a little");

        RecipeCategory category1 = new RecipeCategory("Vegan");
        RecipeCategory category2 = new RecipeCategory("Chicken");
        RecipeCategory category3 = new RecipeCategory("Lunch");

        Recipe recipeData1 = new Recipe("Noodles", recipeInstruction1);
        Recipe recipeData2 = new Recipe("soup", recipeInstruction2);
        Recipe recipeData3 = new Recipe("Rice");
        recipeData1.addCategory(category1);
        recipeData1.addCategory(category2);
        recipeData2.addCategory(category2);
        recipeData2.addCategory(category3);
        recipeData1.addRecipeIngredient(recipeIngredient1);


       createdRecipe1 = recipeRepository.save(recipeData1);
       createdRecipe2 = recipeRepository.save(recipeData2);

       assertNotNull(createdRecipe1);
        assertNotNull(createdRecipe1);
    }

    @Test
    public void test_recipeNameContains(){
        String name = "Noodle";
        List<Recipe> actual = recipeRepository.findAllByRecipeNameContains(name);
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

    @Test
    public void test_ingredientName(){
        assertEquals(1, recipeRepository.selectRecipesByIngredientName(almondMilk).size());
    }

    @Test
    public void test_categoryName(){
        List<Recipe> actual = recipeRepository.selectByCategory("Vegan");
        assertEquals(1, actual.size());
    }

    @Test
    public void test_categories(){
        List<String> result = Arrays.asList(
                "Chicken", "Lunch"
        );
        assertEquals(3, recipeRepository.selectAllByRecipeThatMatchOneOrMoreCategories(result).size());
    }
}