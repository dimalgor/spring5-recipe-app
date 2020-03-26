package guru.springframework.damain;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uof;

    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUof() {
        return uof;
    }

    public void setUof(UnitOfMeasure uof) {
        this.uof = uof;
    }
}
