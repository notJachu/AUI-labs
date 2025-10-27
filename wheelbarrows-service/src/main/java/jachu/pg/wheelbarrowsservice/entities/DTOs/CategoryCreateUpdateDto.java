package jachu.pg.wheelbarrowsservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreateUpdateDto {
    String name;
    int carryWeight;
}
