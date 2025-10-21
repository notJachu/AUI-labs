package jachu.pg.auilabs.lab1;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Comparable<Category>, Serializable {

    @Id
    private UUID uuid;
    @Column(name = "category_name")
    private String name;
    @Column(name = "carry_weight")
    private int carryWeight;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
