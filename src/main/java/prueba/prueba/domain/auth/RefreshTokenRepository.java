package prueba.prueba.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.prueba.infrastructure.entity.RefreshTokenEntity;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    RefreshTokenEntity save(RefreshTokenEntity refreshToken);

    Optional<RefreshTokenEntity> findByToken(String token);

    void delete(RefreshTokenEntity refreshToken);

    List<RefreshTokenEntity> findByUserId(Long userId);
}
