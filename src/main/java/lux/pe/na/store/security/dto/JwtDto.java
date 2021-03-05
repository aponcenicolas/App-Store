package lux.pe.na.store.security.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;


}
