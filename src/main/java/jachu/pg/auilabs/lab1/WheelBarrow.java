package jachu.pg.auilabs.lab1;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "wheelbarrows")
public class WheelBarrow implements Comparable<WheelBarrow>, Serializable {
    @Id
    private UUID uuid;

    @Column(name = "wheelbarrow_name")
    private String name;
    @Column(name = "price")
    private int price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getWheelBarrows().contains(this)) {
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
