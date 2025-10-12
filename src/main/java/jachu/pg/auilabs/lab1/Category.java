package jachu.pg.auilabs.lab1;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category>, Serializable {
    private String name;
    private int someValue;

    @Builder.Default
    @ToString.Exclude
    private List<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        if (element == null) return;
        if (!elements.contains(element)) {
            elements.add(element);
        }
        if (element.getCategory() != this) {
            element.setCategory(this);
        }
    }

    public void removeElement(Element element) {
        if (element == null) return;
        if (elements.remove(element)) {
            element.setCategory(null);
        }
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
