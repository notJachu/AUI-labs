package jachu.pg.auilabs.services;

import jachu.pg.auilabs.entities.Category;
import jachu.pg.auilabs.entities.WheelBarrow;
import jachu.pg.auilabs.repositories.ElementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class WheelBarrowService {
    private final ElementRepository elementRepository;

    @Autowired
    public WheelBarrowService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    public List<WheelBarrow> findAll() {
        return elementRepository.findAll();
    }

    @Transactional
    public List<WheelBarrow> findAllByCategory(Category category) {
        return elementRepository.findAllByCategory(category);
    }

    public Optional<WheelBarrow> findById(UUID uuid) {
        return elementRepository.findByUuid(uuid);
    }

    public WheelBarrow save(WheelBarrow wheelBarrow) {
        return elementRepository.save(wheelBarrow);
    }

    public void deleteByUuid(UUID uuid) {
        elementRepository.deleteById(uuid);
    }


    public void deleteAll() {
        elementRepository.deleteAll();
    }

    @Transactional
    public Object createWheelBarrow(String name, int price, Category category) {
        WheelBarrow wheelBarrow = WheelBarrow.builder()
                .name(name)
                .price(price)
                .category(category)
                .build();
        return elementRepository.save(wheelBarrow);
    }
}
