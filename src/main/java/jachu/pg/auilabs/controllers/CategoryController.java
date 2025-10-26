package jachu.pg.auilabs.controllers;

import jachu.pg.auilabs.entities.Category;
import jachu.pg.auilabs.entities.DTOs.CategoryCollectionDto;
import jachu.pg.auilabs.entities.DTOs.CategoryReadDto;
import jachu.pg.auilabs.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryController categoryController;
    private final CategoryService categoryService;

    public CategoryController(CategoryController categoryController, CategoryService categoryService) {
        this.categoryController = categoryController;
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
}
