package prueba.prueba.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prueba.prueba.domain.tarea.Tarea;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<Tarea> tareas = new ArrayList<>();
}
