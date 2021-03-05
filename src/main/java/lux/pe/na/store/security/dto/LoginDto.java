package lux.pe.na.store.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "Nombre de usuario requerido")
    private String userName;

    @NotBlank(message = "Contraseña requerido")
    private String password;
}
