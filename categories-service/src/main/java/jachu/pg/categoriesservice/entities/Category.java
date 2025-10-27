package jachu.pg.categoriesservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Comparable<Category>, Serializable {

    @Id
    private UUID uuid = UUID.randomUUID();
    @Column(name = "category_name")
    private String name;
    @Column(name = "carry_weight")
    private int carryWeight;



    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
