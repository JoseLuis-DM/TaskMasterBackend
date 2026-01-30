package prueba.prueba.domain.categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Categoria guardar(Categoria categoria);

    Categoria update(Categoria categoria);

    Optional<Categoria> findById(Long userId);

    List<Categoria> findAll();

    void deleteById(Long id);
}
