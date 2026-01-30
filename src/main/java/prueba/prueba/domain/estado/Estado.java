package prueba.prueba.domain.estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import prueba.prueba.domain.tarea.Tarea;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Estado {

    private Long id;
    private String nombre;

    @Builder.Default
    private List<Tarea> tarea = new ArrayList<>();
}
