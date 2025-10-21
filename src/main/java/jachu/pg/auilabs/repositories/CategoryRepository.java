package jachu.pg.auilabs.repositories;

import jachu.pg.auilabs.lab1.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
