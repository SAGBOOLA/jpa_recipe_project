package org.example.jpa_recipe_project.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jpa_recipe_project.entity.Ingredient;
import org.example.jpa_recipe_project.entity.Measurement;
import org.example.jpa_recipe_project.entity.RecipeIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class RecipeIngredientRepositoryTest {

    @Autowired
    RecipeIngredientRepository testObject;

    RecipeIngredient createdData;
    @BeforeEach
    public void setup(){
        Ingredient ingredient = new Ingredient("Sugar");
        RecipeIngredient test = new RecipeIngredient(ingredient, 20, Measurement.G);
        createdData = testObject.save(test);
    }

    @Test
    public void test_create(){
        /*Ingredient test = new Ingredient("salt");
        RecipeIngredient test1 = new RecipeIngredient(test, 10, Measurement.G);*/
        assertNotNull(createdData);
    }

    @Test
    public void test_update(){
        RecipeIngredient expected = new RecipeIngredient(createdData.getId(), createdData.getIngredient(), 15, Measurement.G);
        Optional<RecipeIngredient> result = testObject.findById(createdData.getId());
        assertTrue(result.isPresent());
        result.get().setAmount(15);
        RecipeIngredient actual = testObject.save(result.get());
        assertEquals(expected, actual);
    }

    @Test
    public void test_delete(){
        testObject.delete(createdData);
        Optional<RecipeIngredient> actual = testObject.findById(createdData.getId());
        assertFalse(actual.isPresent());
    }
}
