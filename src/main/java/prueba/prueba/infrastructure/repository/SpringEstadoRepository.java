package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.prueba.infrastructure.entity.EstadoEntity;

import java.util.Optional;

public interface SpringEstadoRepository extends JpaRepository<EstadoEntity, Long> {

    Optional<EstadoEntity> findByNombre(String nombre);
}
