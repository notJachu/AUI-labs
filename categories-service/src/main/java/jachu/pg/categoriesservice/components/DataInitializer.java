package jachu.pg.categoriesservice.components;

import jachu.pg.categoriesservice.entities.Category;
import jachu.pg.categoriesservice.services.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final CategoryService categoryService;

    public DataInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostConstruct
    public void init() {
        Category categoryOne = Category.builder()
                .uuid(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .name("Small")
                .carryWeight(4)
                .build();

        Category categoryTwo = Category.builder()
                .uuid(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .name("Large")
                .carryWeight(62)
                .build();

        // Save categories; CascadeType.ALL on Category.wheelBarrows will persist wheelbarrows
        categoryService.save(categoryOne);
        categoryService.save(categoryTwo);

        System.out.println("Initialized categories in category service");
    }

    @PreDestroy
    public void clean() {
        categoryService.deleteAll();
        //wheelBarrowService.deleteAll();
        System.out.println("Cleaned up categories in category service");
    }
}