package jachu.pg.categoriesservice.services;

import jachu.pg.categoriesservice.entities.Category;
import jachu.pg.categoriesservice.repos.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(UUID uuid) {
        return categoryRepository.findByUuid(uuid);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteByUuid(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

}