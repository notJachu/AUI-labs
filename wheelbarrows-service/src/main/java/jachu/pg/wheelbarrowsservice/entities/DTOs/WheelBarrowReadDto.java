package jachu.pg.wheelbarrowsservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WheelBarrowReadDto {
    UUID id;
    String name;
    int price;
    CategoryCollectionDto category;
}

