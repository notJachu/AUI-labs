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
    private List<WheelBurrow> wheelBurrows = new ArrayList<>();

    public void addElement(WheelBurrow wheelBurrow) {
        if (wheelBurrow == null) return;
        if (!wheelBurrows.contains(wheelBurrow)) {
            wheelBurrows.add(wheelBurrow);
        }
        if (wheelBurrow.getCategory() != this) {
            wheelBurrow.setCategory(this);
        }
    }

    public void removeElement(WheelBurrow wheelBurrow) {
        if (wheelBurrow == null) return;
        if (wheelBurrows.remove(wheelBurrow)) {
            wheelBurrow.setCategory(null);
        }
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
