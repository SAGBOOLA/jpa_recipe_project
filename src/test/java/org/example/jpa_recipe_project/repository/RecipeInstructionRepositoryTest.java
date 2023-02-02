package org.example.jpa_recipe_project.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jpa_recipe_project.entity.RecipeInstruction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class RecipeInstructionRepositoryTest {

    @Autowired
    RecipeInstructionRepository testObject;

    RecipeInstruction createdInstruction;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    public void setup(){
        RecipeInstruction instruction = new RecipeInstruction("Start recipe");
        createdInstruction = testObject.save(instruction);
    }

    @Test
    public void test_create(){
        RecipeInstruction test = new RecipeInstruction("How to cook");
        assertNotNull(testObject.save(test));
    }

    @Test
    public void test_findAll(){
        int expected = 1;
        Iterable<RecipeInstruction> iterable = testObject.findAll();
        List<RecipeInstruction> recipeInstructionList = new ArrayList<>();
        iterable.forEach(recipeInstructionList::add);
        assertEquals(expected,recipeInstructionList.size());

        /*testObject.findAll();
        assertNotNull(testEntityManager.find(RecipeInstruction.class, createdInstruction.getId()));*/
    }

}
