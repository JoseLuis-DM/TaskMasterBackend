package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.prueba.infrastructure.entity.EstadoEntity;

public interface SpringEstadoRepository extends JpaRepository<EstadoEntity, Long> {
}
