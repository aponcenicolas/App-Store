package lux.pe.na.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {

    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String name;

    @NotNull
    @Positive(message = "Stock requerido - invalido")
    private int stock;

    @NotNull
    @Positive(message = "Precio requerido - invalido")
    private double price;

    @NotNull(message = "CategoriaId requerido - invalido")
    @Positive(message = "CategoriaId no debe ser 0")
    private Long categoryId;

    private boolean status;
}
