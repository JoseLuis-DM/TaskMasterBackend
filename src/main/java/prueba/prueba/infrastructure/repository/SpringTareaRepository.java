package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba.prueba.infrastructure.entity.TareaEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringTareaRepository extends JpaRepository<TareaEntity, Long>, JpaSpecificationExecutor<TareaEntity> {

    Optional<TareaEntity> findByIdAndUsuario_Id(Long id, Long userId);

    @EntityGraph(attributePaths = {"categoria", "estado"})
    List<TareaEntity> findAllByUsuarioId(Long usuarioId);

    @Query("SELECT t.categoria.nombre, COUNT(t) FROM TareaEntity t WHERE t.usuario.id = :usuarioId GROUP BY t.categoria.nombre")
    List<Object[]> countTareasPorCategoria(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(t) FROM TareaEntity t WHERE t.usuario.id = :usuarioId")
    Long countTotalTareas(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(t) FROM TareaEntity t WHERE t.usuario.id = :usuarioId AND t.estado.nombre = 'Completada'")
    Long countTareasCompletadas(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(t) FROM TareaEntity t WHERE t.usuario.id = :usuarioId AND t.estado.nombre = 'Pendiente'")
    Long countTareasPendientes(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(t) FROM TareaEntity t WHERE t.usuario.id = :usuarioId AND t.estado.nombre = 'Atrasada'")
    Long countTareasAtrasadas(@Param("usuarioId") Long usuarioId);

    List<TareaEntity> findTop6ByUsuarioIdOrderByLimiteDesc(Long usuarioId);
}
