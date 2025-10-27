package jachu.pg.wheelbarrowsservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wheelbarrows")
public class WheelBarrow implements Comparable<WheelBarrow>, Serializable {
    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(name = "wheelbarrow_name")
    private String name;
    @Column(name = "price")
    private int price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private Category category;


    @Builder
    public WheelBarrow(String name, int price, Category category) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
        setCategory(category);
    }


    @Override
    public int compareTo(WheelBarrow o) {
        return this.name.compareTo(o.name);
    }
}
