package jachu.pg.auilabs.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryReadDto {
    UUID uuid;
    String name;
    int carryWeight;
    List<WheelBarrowCollectionDto> wheelBarrowCollectionDtoList;
}
