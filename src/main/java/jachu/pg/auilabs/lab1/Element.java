package jachu.pg.auilabs.lab1;


import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Element implements Comparable<Element>{
    private String name;
    private int value;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category = null;

    public void setCategory(Category category) {
        if(this.category != null) {
            this.category.getElemtns().remove(this);
        }
        this.category = category;
        if(category != null && !category.getElemtns().contains(this)) {
            category.getElemtns().add(this);
        }
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
