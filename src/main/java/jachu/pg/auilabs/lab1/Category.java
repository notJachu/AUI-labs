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
    private int carryWeight;

    @Builder.Default
    @ToString.Exclude
    private List<WheelBarrow> wheelBarrows = new ArrayList<>();

    public void addElement(WheelBarrow wheelBarrow) {
        if (wheelBarrow == null) return;
        if (!wheelBarrows.contains(wheelBarrow)) {
            wheelBarrows.add(wheelBarrow);
        }
        if (wheelBarrow.getCategory() != this) {
            wheelBarrow.setCategory(this);
        }
    }

    public void removeElement(WheelBarrow wheelBarrow) {
        if (wheelBarrow == null) return;
        if (wheelBarrows.remove(wheelBarrow)) {
            wheelBarrow.setCategory(null);
        }
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
