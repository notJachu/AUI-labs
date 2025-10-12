package jachu.pg.auilabs.lab1;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Category {
    @Getter
    @Setter
    String name;
    int someValue;
    List<Element> elemtns;
}
