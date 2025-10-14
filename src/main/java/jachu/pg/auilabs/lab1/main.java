package jachu.pg.auilabs.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {

        Category category_one = Category.builder()
                .name("Small")
                .carryWeight(4)
                .build();

        Category category_two = Category.builder()
                .name("Large")
                .carryWeight(62)
                .build();

        WheelBurrow wheelBurrow_one = WheelBurrow.builder()
                .name("My first wheel burrow")
                .price(5)
                .category(category_one)
                .build();

        WheelBurrow wheelBurrow_two = WheelBurrow.builder()
                .name("Wheel burrow for adults")
                .price(15)
                .category(category_two)
                .build();

        WheelBurrow wheelBurrow_three = WheelBurrow.builder()
                .name("Wheel burrow playset")
                .price(5)
                .category(category_one)
                .build();

        // Task 3
        System.out.println("--- Task 3 ---");

        List<Category> categories = List.of(category_one, category_two);

        categories.forEach(category -> {
            System.out.println(category);
            category.getWheelBurrows().forEach(System.out::println);
        });

        Set<WheelBurrow> allWheelBurrows = categories.stream()
                .flatMap(cat -> cat.getWheelBurrows().stream())
                .collect(Collectors.toSet());

        allWheelBurrows.stream().forEach(System.out::println);

        // Task 4
        System.out.println("--- Task 4 ---");
        allWheelBurrows.stream()
                .filter(wheelBurrow -> wheelBurrow.getPrice() == 5)
                .sorted()
                .forEach(System.out::println);

        // Task 5
        System.out.println("--- Task 5 ---");
        List<ElementDto> elementDtos = allWheelBurrows.stream()
                .map(WheelBurrow::toDto)
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
            category.getWheelBurrows().forEach(System.out::println);
        });



        // Task 7
        System.out.println("--- Task 7 ---");
        long waitTime = 1000L;
        ForkJoinPool forkJoinPool = new ForkJoinPool(2); // 2 workers

        try {
            forkJoinPool.submit(() ->
                    categories.parallelStream()
                            .forEach(cat -> processCategory(cat, waitTime))
            ).join();
        } finally {
            forkJoinPool.shutdown();
            try {
                forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void processCategory(Category cat, long waitTime) {
        cat.getWheelBurrows().forEach(System.out::println);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Processed category: " + cat.getName());
    }
}




