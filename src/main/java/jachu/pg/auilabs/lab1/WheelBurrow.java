package jachu.pg.auilabs.lab1;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
public class WheelBurrow implements Comparable<WheelBurrow>, Serializable {
    private String name;
    private int price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;


    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getWheelBurrows().contains(this)) {
            category.addElement(this);
        }
    }

    @Builder
    public WheelBurrow(String name, int price, Category category) {
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
    public int compareTo(WheelBurrow o) {
        return this.name.compareTo(o.name);
    }
}
