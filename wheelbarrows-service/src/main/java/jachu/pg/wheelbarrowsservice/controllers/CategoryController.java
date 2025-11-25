package jachu.pg.wheelbarrowsservice.controllers;

import jachu.pg.wheelbarrowsservice.entities.Category;
import jachu.pg.wheelbarrowsservice.entities.DTOs.CategoryCollectionDto;
import jachu.pg.wheelbarrowsservice.entities.DTOs.CategoryCreateUpdateDto;
import jachu.pg.wheelbarrowsservice.entities.DTOs.WheelBarrowCreateUpdateDto;
import jachu.pg.wheelbarrowsservice.services.CategoryService;
import jachu.pg.wheelbarrowsservice.services.WheelBarrowService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final WheelBarrowService wheelBarrowService;

    public CategoryController(CategoryService categoryService, WheelBarrowService wheelBarrowService) {
        this.wheelBarrowService = wheelBarrowService;
        this.categoryService = categoryService;
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
    public ResponseEntity<CategoryCollectionDto> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        CategoryCollectionDto categoryDto = new CategoryCollectionDto(
                category.getUuid(),
                category.getName()
        );

        return ResponseEntity.ok(categoryDto);
    }

    // saving category sent from category service
    @PostMapping("")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryCreateUpdateDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCarryWeight(categoryDto.getCarryWeight());
        category.setUuid(categoryDto.getUuid());

        categoryService.save(category);
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
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/createWheelBarrow")
    public ResponseEntity<Void> createWheelBarrow(@PathVariable UUID id, @RequestBody WheelBarrowCreateUpdateDto wheelBarrowDto) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optionalCategory.get();

        wheelBarrowService.createWheelBarrow(
                wheelBarrowDto.getName(),
                wheelBarrowDto.getPrice(),
                category
        );

        return ResponseEntity.ok().build();
    }

}
