package org.example.jpa_recipe_project.repository;

import org.example.jpa_recipe_project.entity.Ingredient;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository testObject;

    Ingredient createdIngredient;

    @BeforeEach
    public void setUp() {
        Ingredient ingredientData1 = new Ingredient("test");
        createdIngredient = testObject.save(ingredientData1);
        assertNotNull(ingredientData1);
    }

    @Test
    public void test_findById() {
        Ingredient expected = createdIngredient;
        Optional<Ingredient> actual = testObject.findById(createdIngredient.getId());
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findByIngredientName() {
        Optional<Ingredient> actual = testObject.findByIngredientName(createdIngredient.getIngredientName());
        Ingredient expected = createdIngredient;
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findAllByIngredientNameContains() {
        List<Ingredient> allByIngredientNameContains = testObject.findAllByIngredientNameContains(createdIngredient.getIngredientName());
        assertNotNull(allByIngredientNameContains);
        List<Ingredient> expected = new ArrayList<>();
        expected.add(createdIngredient);
        assertEquals(expected, allByIngredientNameContains);
    }

    @Test
    public void test_remove() {
        testObject.deleteById(createdIngredient.getId());
        Optional<Ingredient> result = testObject.findById(createdIngredient.getId());
        assertFalse(result.isPresent());

       /*Optional<Ingredient> actual = testObject.findById(createdIngredient.getId());
       assertTrue(actual.isPresent());
       Ingredient actualResult = actual.get();
     Ingredient test =  testObject.delete(actualResult);
       Ingredient expected = createdIngredient;
      assertNull(actualResult);*/
        //assertNull(testObject.deleteById(createdIngredient.getId()));
    }

    @Test
    public void test_delete(){
        testObject.delete(createdIngredient);
        Optional<Ingredient> result = testObject.findByIngredientName(createdIngredient.getIngredientName());
        assertFalse(result.isPresent());
    }

    @Test
    public void test_update(){
        Ingredient expected = createdIngredient;
        Optional<Ingredient> result = testObject.findById(createdIngredient.getId());
        assertTrue(result.isPresent());
        result.get().setIngredientName("Pasta");
        Ingredient actual = testObject.save(result.get());
        assertEquals(expected,actual);
    }
}