package prueba.prueba.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    @JsonIgnore
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String rol;
}
