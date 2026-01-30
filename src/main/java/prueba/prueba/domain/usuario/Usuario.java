package prueba.prueba.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private Rol rol;
}
