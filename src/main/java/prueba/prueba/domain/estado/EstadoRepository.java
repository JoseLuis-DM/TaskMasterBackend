package prueba.prueba.domain.estado;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository {

    Estado guardar(Estado estado);

    Optional<Estado> findById(Long userId);

    List<Estado> findAll();

    void deleteById(Long id);
}
