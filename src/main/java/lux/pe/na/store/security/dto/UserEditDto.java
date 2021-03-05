package lux.pe.na.store.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserEditDto {

    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String name;

    @NotBlank(message = "Contraseña requerido")
    private String password;

    private Set<String> roles = new HashSet<>();
}
