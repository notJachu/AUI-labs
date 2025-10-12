package jachu.pg.auilabs.lab1;

import java.io.*;
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

        // Task 3
        System.out.println("--- Task 3 ---");

        List<Category> categories = List.of(category_one, category_two);

        categories.forEach(category -> {
            System.out.println(category);
            category.getElements().forEach(System.out::println);
        });

        Set<Element> allElements = categories.stream()
                .flatMap(cat -> cat.getElements().stream())
                .collect(Collectors.toSet());

        allElements.stream().forEach(System.out::println);

        // Task 4
        System.out.println("--- Task 4 ---");
        allElements.stream()
                .filter(element -> element.getValue() == 5)
                .sorted()
                .forEach(System.out::println);

        // Task 5
        System.out.println("--- Task 5 ---");
        List<ElementDto> elementDtos = allElements.stream()
                .map(Element::toDto)
                .toList();

        elementDtos.forEach(System.out::println);

        // Task  6
        System.out.println("--- Task 6 ---");

        File file = new File("categories.ser");

        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(categories);
            oos.flush();
        }  catch (Exception e) {
            System.out.println(e);
        }

        List<Category> deserializedCategories = new ArrayList<>();
        try (var fis = new FileInputStream(file); var ois = new ObjectInputStream(fis)) {
            deserializedCategories = (List<Category>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        deserializedCategories.forEach(category -> {
            System.out.println(category);
            category.getElements().forEach(System.out::println);
        });
    }
}
