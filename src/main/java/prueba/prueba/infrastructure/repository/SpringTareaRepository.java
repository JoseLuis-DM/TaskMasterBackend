package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.prueba.infrastructure.entity.TareaEntity;

import java.util.Optional;

@Repository
public interface SpringTareaRepository extends JpaRepository<TareaEntity, Long> {

    Optional<TareaEntity> findByIdAndUsuario_Id(Long id, Long userId);
}
