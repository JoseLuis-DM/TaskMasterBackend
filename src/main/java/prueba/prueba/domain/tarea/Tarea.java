package prueba.prueba.domain.tarea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.domain.usuario.Usuario;

import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Tarea {

    private Long id;
    private String titulo;
    private String descripcion;
    private Usuario usuario;
    private Categoria categoria;
    private Estado estado;
    private LocalDate limite;
}
