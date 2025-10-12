package jachu.pg.auilabs.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {

        Category category_one = Category.builder()
                .name("cat1")
                .someValue(4)
                .build();

        Category category_two = Category.builder()
                .name("cat2")
                .someValue(62)
                .build();

        Element element_one = Element.builder()
                .name("el1")
                .value(5)
                .category(category_one)
                .build();

        Element element_two = Element.builder()
                .name("el2")
                .value(15)
                .category(category_two)
                .build();

        Element element_three = Element.builder()
                .name("ml3")
                .value(5)
                .category(category_one)
                .build();

        List<Category> categories = List.of(category_one, category_two);

        categories.forEach(category -> {
            System.out.println(category);
            category.getElements().forEach(System.out::println);
        });

        Set<Element> allElements = categories.stream()
                .flatMap(cat -> cat.getElements().stream())
                .collect(Collectors.toSet());

        allElements.stream().forEach(System.out::println);

        allElements.stream()
                .filter(element -> element.getValue() == 5)
                .sorted()
                .forEach(System.out::println);
    }

}
