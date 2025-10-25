package jachu.pg.auilabs.lab1;


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


    public void setCategory(Category category) {
        Hibernate.initialize(category);
        this.category = category;
        if (category != null) {
            category.addElement(this);
        }
    }

    @Builder
    public WheelBarrow(String name, int price, Category category) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
        setCategory(category);
    }

    public ElementDto toDto() {
        return ElementDto.builder()
                .name(this.name)
                .value(this.price)
                .categoryName(this.category != null ? this.category.getName() : null)
                .build();
    }

    @Override
    public int compareTo(WheelBarrow o) {
        return this.name.compareTo(o.name);
    }
}
