package jachu.pg.auilabs.repositories;

import jachu.pg.auilabs.lab1.WheelBarrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ElementRepository extends JpaRepository<WheelBarrow, UUID> {
}
