package jachu.pg.auilabs.lab1;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category>{
    private String name;
    private int someValue;

    @Builder.Default
    @ToString.Exclude
    private List<Element> elemtns = new ArrayList<>();

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}
