package jachu.pg.auilabs.lab1;


import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

@Data
@Builder
@NoArgsConstructor
public class Element implements Comparable<Element>{
    private String name;
    private int value;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;


    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getElements().contains(this)) {
            category.addElement(this);
        }
    }

    @Builder
    public Element(String name, int value, Category category) {
        this.name = name;
        this.value = value;
        setCategory(category);
    }

    public ElementDto toDto() {
        return ElementDto.builder()
                .name(this.name)
                .value(this.value)
                .categoryName(this.category != null ? this.category.getName() : null)
                .build();
    }

    @Override
    public int compareTo(Element o) {
        return this.name.compareTo(o.name);
    }
}
