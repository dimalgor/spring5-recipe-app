package guru.springframework.bootstrap;

import guru.springframework.damain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading Bootstrap Data");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOM
        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUOMOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupsUOMOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupsUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        // get Optionals
        UnitOfMeasure eachUOM = eachUOMOptional.get();
        UnitOfMeasure teaspoonUOM = teaspoonUOMOptional.get();
        UnitOfMeasure tablespoonUOM = tablespoonUOMOptional.get();
        UnitOfMeasure dashUOM = dashUOMOptional.get();
        UnitOfMeasure pintUOM = pintUOMOptional.get();
        UnitOfMeasure cupsUOM = cupsUOMOptional.get();

        // get Categories

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacomole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirection("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.");
        guacRecipe.setNotes(guacNotes);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("ripe avocados");
        ingredient1.setAmount(2);
        ingredient1.setUom(eachUOM);
        ingredient1.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("Kosher salt");
        ingredient2.setAmount(5);
        ingredient2.setRecipe(guacRecipe);
        ingredient2.setUom(teaspoonUOM);
        guacRecipe.addIngredient(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("Fresh lime juice or lemon juice");
        ingredient3.setAmount(2);
        ingredient3.setUom(tablespoonUOM);
        ingredient3.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient3);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("Minced red onion or thinly sliced green onion");
        ingredient4.setAmount(2);
        ingredient4.setUom(tablespoonUOM);
        ingredient4.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient4);

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setDescription("serrano chiles, steams and seeds removed, minced");
        ingredient5.setAmount(2);
        ingredient5.setUom(eachUOM);
        ingredient5.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient5);

        Ingredient ingredient6 = new Ingredient();
        ingredient6.setDescription("Cilantro");
        ingredient6.setAmount(2);
        ingredient6.setUom(tablespoonUOM);
        ingredient6.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient6);

        Ingredient ingredient7 = new Ingredient();
        ingredient7.setDescription("Freshly grated black pepper");
        ingredient7.setAmount(2);
        ingredient7.setUom(dashUOM);
        ingredient7.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient7);

        Ingredient ingredient8 = new Ingredient();
        ingredient8.setDescription("Ripe tomato, seeds and pulp removed, chopped");
        ingredient8.setAmount(2);
        ingredient8.setUom(eachUOM);
        ingredient8.setRecipe(guacRecipe);
        guacRecipe.addIngredient(ingredient8);

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply recipes");

        //add to return list
        recipes.add(guacRecipe);

        //Yummy tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy grilled chicken tacos");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirection("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        Notes tacNotes = new Notes();
        tacNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n");
        tacosRecipe.setNotes(tacNotes);


        Ingredient tacoIngr1 = new Ingredient();
        tacoIngr1.setDescription("Ancho chili powder");
        tacoIngr1.setAmount(2);
        tacoIngr1.setUom(tablespoonUOM);
        tacoIngr1.setRecipe(tacosRecipe);
        tacosRecipe.addIngredient(tacoIngr1);

        Ingredient tacoIngr2 = new Ingredient();
        tacoIngr2.setDescription("Dried oregano");
        tacoIngr2.setAmount(1);
        tacoIngr2.setUom(teaspoonUOM);
        tacoIngr2.setRecipe(tacosRecipe);
        tacosRecipe.addIngredient(tacoIngr2);

        Ingredient tacoIngr3 = new Ingredient();
        tacoIngr3.setDescription("Dried cumin");
        tacoIngr3.setAmount(1);
        tacoIngr3.setUom(teaspoonUOM);
        tacoIngr3.setRecipe(tacosRecipe);
        tacosRecipe.addIngredient(tacoIngr3);

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }
}
