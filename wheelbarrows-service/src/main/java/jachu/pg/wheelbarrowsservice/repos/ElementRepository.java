package jachu.pg.wheelbarrowsservice.repos;

import jachu.pg.wheelbarrowsservice.entities.Category;
import jachu.pg.wheelbarrowsservice.entities.WheelBarrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ElementRepository extends JpaRepository<WheelBarrow, UUID> {
    Optional<WheelBarrow> findByUuid(UUID uuid);
    Optional<WheelBarrow> findByName(String name);
    List<WheelBarrow> findAllByCategory(Category category);
}
