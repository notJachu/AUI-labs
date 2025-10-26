package jachu.pg.auilabs.controllers;

import jachu.pg.auilabs.entities.Category;
import jachu.pg.auilabs.entities.DTOs.CategoryCollectionDto;
import jachu.pg.auilabs.entities.DTOs.CategoryCreateUpdateDto;
import jachu.pg.auilabs.entities.DTOs.CategoryReadDto;
import jachu.pg.auilabs.entities.DTOs.WheelBarrowCreateUpdateDto;
import jachu.pg.auilabs.services.CategoryService;
import jachu.pg.auilabs.services.WheelBarrowService;
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
    public ResponseEntity<CategoryReadDto> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        CategoryReadDto categoryDto = new CategoryReadDto(
                category.getUuid(),
                category.getName(),
                category.getCarryWeight(),
                category.getWheelBarrows().stream()
                        .map(wheelBarrow -> new jachu.pg.auilabs.entities.DTOs.WheelBarrowCollectionDto(
                                wheelBarrow.getUuid(),
                                wheelBarrow.getName()
                        ))
                        .toList()
        );

        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping("")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryCreateUpdateDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCarryWeight(categoryDto.getCarryWeight());

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
