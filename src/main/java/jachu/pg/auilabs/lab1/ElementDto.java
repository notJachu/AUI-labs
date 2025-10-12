package jachu.pg.auilabs.lab1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementDto {

    private String name;
    private int value;
    private String categoryName;
}
