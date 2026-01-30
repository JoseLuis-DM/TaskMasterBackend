package prueba.prueba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.prueba.infrastructure.entity.UsuarioEntity;

import java.util.Optional;

@Repository
public interface SpringUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);
}
