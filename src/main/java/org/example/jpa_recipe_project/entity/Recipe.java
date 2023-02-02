package org.example.jpa_recipe_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private String recipeName;
    @OneToMany(mappedBy = "recipe",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "instruction_id")
    private RecipeInstruction instruction;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_category_id")
    )
    private List<RecipeCategory> categories = new ArrayList<>();

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction instruction, List<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.instruction = instruction;
        this.categories = categories;
    }

    public Recipe(String recipeName, RecipeInstruction instruction) {
        this.recipeName = recipeName;
        this.instruction = instruction;
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) throw new IllegalArgumentException("recipeIngredient was null");
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) throw new IllegalArgumentException("recipeIngredient was null");
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);
    }

    public void addCategory(RecipeCategory category){
        if (category == null) throw new IllegalArgumentException("category was null");
        if (categories == null) categories = new ArrayList<>();

        categories.add(category);
    }

    public void removeRecipe(RecipeCategory category){
        if (category == null) throw new IllegalArgumentException("recipe1 was null");
        if (categories != null) categories.remove(category);
    }

}
