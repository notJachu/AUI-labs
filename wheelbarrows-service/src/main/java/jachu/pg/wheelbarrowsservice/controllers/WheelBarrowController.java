package jachu.pg.wheelbarrowsservice.controllers;

import jachu.pg.wheelbarrowsservice.entities.DTOs.WheelBarrowReadDto;
import jachu.pg.wheelbarrowsservice.entities.WheelBarrow;
import jachu.pg.wheelbarrowsservice.services.WheelBarrowService;
import jachu.pg.wheelbarrowsservice.entities.DTOs.CategoryCollectionDto;
import jachu.pg.wheelbarrowsservice.entities.DTOs.WheelBarrowCollectionDto;
import jachu.pg.wheelbarrowsservice.entities.DTOs.WheelBarrowCreateUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/wheelbarrows")
public class WheelBarrowController {

    private final WheelBarrowService wheelBarrowService;

    public WheelBarrowController(WheelBarrowService wheelBarrowService) {
        this.wheelBarrowService = wheelBarrowService;
    }

    @GetMapping("")
    public List<WheelBarrowCollectionDto> getWheelBarrows() {
        List<WheelBarrow> wheelBarrows = wheelBarrowService.findAll();
        List<WheelBarrowCollectionDto> wheelBarrowDtos = wheelBarrows.stream()
                .map(wheelBarrow -> new WheelBarrowCollectionDto(
                        wheelBarrow.getUuid(),
                        wheelBarrow.getName()
                ))
                .toList();
        return wheelBarrowDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WheelBarrowReadDto> getWheelBarrowById(@PathVariable UUID id) {
        WheelBarrow wheelBarrow = wheelBarrowService.findById(id).orElse(null);

        if (wheelBarrow == null) {
            return ResponseEntity.notFound().build();
        }

        WheelBarrowReadDto wheelBarrowDto = new WheelBarrowReadDto(
                wheelBarrow.getUuid(),
                wheelBarrow.getName(),
                wheelBarrow.getPrice(),
                wheelBarrow.getCategory() != null ? new CategoryCollectionDto(
                        wheelBarrow.getCategory().getUuid(),
                        wheelBarrow.getCategory().getName()
                ) : null
        );
        return ResponseEntity.ok(wheelBarrowDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWheelBarrow(@PathVariable UUID id, @RequestBody WheelBarrowCreateUpdateDto wheelBarrowDto) {

        WheelBarrow wheelBarrow = wheelBarrowService.findById(id).orElse(null);

        if (wheelBarrow == null) {
            return ResponseEntity.notFound().build();
        }

        wheelBarrow.setName(wheelBarrowDto.getName());
        wheelBarrow.setPrice(wheelBarrowDto.getPrice());

        wheelBarrowService.save(wheelBarrow);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWheelBarrow(@PathVariable UUID id) {
        Optional<WheelBarrow> wheelBarrow = wheelBarrowService.findById(id);

        if (wheelBarrow.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        wheelBarrowService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
