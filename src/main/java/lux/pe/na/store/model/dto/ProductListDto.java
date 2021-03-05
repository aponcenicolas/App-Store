package lux.pe.na.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductListDto {

    private Long id;
    private String name;
    private int stock;
    private double price;
    private String category;
}
