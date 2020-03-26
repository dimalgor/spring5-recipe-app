package guru.springframework.services;

import guru.springframework.damain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
