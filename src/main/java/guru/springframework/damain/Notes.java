package guru.springframework.damain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
