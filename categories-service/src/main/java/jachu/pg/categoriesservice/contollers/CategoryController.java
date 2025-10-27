package jachu.pg.categoriesservice.contollers;


import jachu.pg.categoriesservice.entities.Category;
import jachu.pg.categoriesservice.entities.DTOs.CategoryCollectionDto;
import jachu.pg.categoriesservice.entities.DTOs.CategoryCreateUpdateDto;
import jachu.pg.categoriesservice.entities.DTOs.CategoryReadDto;
import jachu.pg.categoriesservice.services.CategoryService;
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


}