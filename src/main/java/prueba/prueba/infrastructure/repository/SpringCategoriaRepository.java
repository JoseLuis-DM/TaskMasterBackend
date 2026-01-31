package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.prueba.infrastructure.entity.CategoriaEntity;

import java.util.Optional;

public interface SpringCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNombre(String nombre);
}
