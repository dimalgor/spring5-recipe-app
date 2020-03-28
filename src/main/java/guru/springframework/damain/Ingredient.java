package guru.springframework.damain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
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

}
