package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import prueba.prueba.infrastructure.entity.TareaEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringTareaRepository extends JpaRepository<TareaEntity, Long>, JpaSpecificationExecutor<TareaEntity> {

    Optional<TareaEntity> findByIdAndUsuario_Id(Long id, Long userId);

    @EntityGraph(attributePaths = {"categoria", "estado"})
    List<TareaEntity> findAllByUsuarioId(Long usuarioId);
}
