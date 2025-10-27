package jachu.pg.wheelbarrowsservice.components;

import jachu.pg.wheelbarrowsservice.entities.Category;
import jachu.pg.wheelbarrowsservice.entities.WheelBarrow;
import jachu.pg.wheelbarrowsservice.services.CategoryService;
import jachu.pg.wheelbarrowsservice.services.WheelBarrowService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final CategoryService categoryService;
    private final WheelBarrowService wheelBarrowService;

    public DataInitializer(CategoryService categoryService, WheelBarrowService wheelBarrowService) {
        this.categoryService = categoryService;
        this.wheelBarrowService = wheelBarrowService;
    }
    @PostConstruct
    public void init() {
        Category categoryOne = Category.builder()
                .uuid(UUID.randomUUID())
                .name("Small")
                .carryWeight(4)
                .build();

        Category categoryTwo = Category.builder()
                .uuid(UUID.randomUUID())
                .name("Large")
                .carryWeight(62)
                .build();

        WheelBarrow wheelBarrowOne = WheelBarrow.builder()
                .name("My first wheelbarrow")
                .price(5)
                .category(categoryOne)
                .build();

        WheelBarrow wheelBarrowTwo = WheelBarrow.builder()
                .name("Wheelbarrow for adults")
                .price(15)
                .category(categoryTwo)
                .build();

        WheelBarrow wheelBarrowThree = WheelBarrow.builder()
                .name("Wheelbarrow playset")
                .price(5)
                .category(categoryOne)
                .build();

        // Save categories; CascadeType.ALL on Category.wheelBarrows will persist wheelbarrows
        categoryService.save(categoryOne);
        categoryService.save(categoryTwo);

        wheelBarrowService.save(wheelBarrowOne);
        wheelBarrowService.save(wheelBarrowTwo);
        wheelBarrowService.save(wheelBarrowThree);


        System.out.println("Initialized wheelbarrows in wheelbarrows-service");
    }

    @PreDestroy
    public void clean() {
        // categoryService.deleteAll();
        wheelBarrowService.deleteAll();
        System.out.println("Cleaned up wheelbarrows in wheelbarrows-service");
    }
}