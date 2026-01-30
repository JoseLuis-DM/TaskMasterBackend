package prueba.prueba.domain.tarea;

import java.util.List;
import java.util.Optional;

public interface TareaRepository {

    Tarea guardar(Tarea tarea);

    Optional<Tarea> findById(Long userId);

    List<Tarea> findAll();

    void deleteById(Long id);

    Optional<Tarea> findByIdAndUsuarioId(Long id, Long userId);
}
