package org.example.jpa_recipe_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient(Ingredient ingredient, double amount, Measurement measurement) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
    }

    public RecipeIngredient(int id, Ingredient ingredient, double amount, Measurement measurement) {
        this.id = id;
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;

    }
}
