package jachu.pg.auilabs.controllers;

import jachu.pg.auilabs.entities.Category;
import jachu.pg.auilabs.entities.DTOs.CategoryCollectionDto;
import jachu.pg.auilabs.entities.DTOs.CategoryCreateUpdateDto;
import jachu.pg.auilabs.entities.DTOs.CategoryReadDto;
import jachu.pg.auilabs.services.CategoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
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
}
