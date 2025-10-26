package jachu.pg.auilabs.controllers;

import jachu.pg.auilabs.entities.DTOs.CategoryCollectionDto;
import jachu.pg.auilabs.entities.DTOs.WheelBarrowCollectionDto;
import jachu.pg.auilabs.entities.DTOs.WheelBarrowReadDto;
import jachu.pg.auilabs.entities.WheelBarrow;
import jachu.pg.auilabs.services.CategoryService;
import jachu.pg.auilabs.services.WheelBarrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


}
