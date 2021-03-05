package lux.pe.na.store.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lux.pe.na.store.security.enums.RoleName;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RoleDto {

    private Long id;

    @NotNull(message = "Nombre de rol requerido")
    private RoleName roleName;
}
