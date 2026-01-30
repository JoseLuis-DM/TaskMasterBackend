package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.prueba.infrastructure.entity.CategoriaEntity;

public interface SpringCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
