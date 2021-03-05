package lux.pe.na.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDto {

    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String name;

    private boolean status;
}
