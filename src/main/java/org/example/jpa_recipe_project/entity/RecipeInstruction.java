package org.example.jpa_recipe_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class RecipeInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private String instructions;

    public RecipeInstruction(String instructions) {
        this.instructions = instructions;
    }
}
