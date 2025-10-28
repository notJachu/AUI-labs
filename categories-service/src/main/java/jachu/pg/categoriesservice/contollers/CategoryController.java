package jachu.pg.categoriesservice.contollers;


import jachu.pg.categoriesservice.entities.Category;
import jachu.pg.categoriesservice.entities.DTOs.CategoryCollectionDto;
import jachu.pg.categoriesservice.entities.DTOs.CategoryCreateUpdateDto;
import jachu.pg.categoriesservice.entities.DTOs.CategoryReadDto;
import jachu.pg.categoriesservice.entities.DTOs.CategorySendDto;
import jachu.pg.categoriesservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {

    private final CategoryService categoryService;
    private final RestTemplate restTemplate;
    private final String wheelBarrowServiceURL;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              RestTemplate restTemplate,
                              @Value("${wheelbarrows.service.url}")
                                  String wheelBarrowServiceURL) {
        this.categoryService = categoryService;
        this.restTemplate = restTemplate;
        this.wheelBarrowServiceURL = wheelBarrowServiceURL;
    }

    @GetMapping("")
    public List<CategoryCollectionDto> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryCollectionDto> categoryDtos = categories.stream()
                .map(category -> new CategoryCollectionDto(
                        category.getUuid(),
                        category.getName()
                ))
                .toList();

        return categoryDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadDto> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        CategoryReadDto categoryDto = new CategoryReadDto(
                category.getUuid(),
                category.getName(),
                category.getCarryWeight()
        );

        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping("")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryCreateUpdateDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCarryWeight(categoryDto.getCarryWeight());

        categoryService.save(category);

        CategorySendDto categorySendDto = CategorySendDto.builder()
                .uuid(category.getUuid())
                .name(category.getName())
                .carryWeight(category.getCarryWeight())
                .build();

        try {
            String eventUrl = wheelBarrowServiceURL + "/categories";
            restTemplate.postForEntity(eventUrl, categorySendDto, Void.class);
        } catch (Exception e) {
            System.err.println("Wheelbarrow Service is not available: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable UUID id, @RequestBody CategoryCreateUpdateDto categoryDto) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setCarryWeight(categoryDto.getCarryWeight());

        categoryService.save(category);

        CategorySendDto categorySendDto = CategorySendDto.builder()
                .uuid(category.getUuid())
                .name(category.getName())
                .carryWeight(category.getCarryWeight())
                .build();

        try {
            String eventUrl = wheelBarrowServiceURL + "/categories/" + category.getUuid();
            restTemplate.put(eventUrl, categorySendDto);
        } catch (Exception e) {
            System.err.println("Wheelbarrow Service is not available: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteByUuid(id);

        try {
            String eventUrl = wheelBarrowServiceURL + "/categories/" + id;
            restTemplate.delete(eventUrl);
        } catch (Exception e) {
            System.err.println("Wheelbarrow Service is not available: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


}