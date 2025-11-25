package jachu.pg.auilabs.components;

import jachu.pg.auilabs.entities.Category;
import jachu.pg.auilabs.entities.WheelBarrow;
import jachu.pg.auilabs.services.CategoryService;
import jachu.pg.auilabs.services.WheelBarrowService;
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

        System.out.println("Initialized categories and wheelbarrows");
    }

    @PreDestroy
    public void clean() {
        //categoryService.deleteAll();
        //wheelBarrowService.deleteAll();
        System.out.println("Cleaned up categories and wheelbarrows");
    }
}
