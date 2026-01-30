package prueba.prueba.application;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.auth.RefreshTokenRepository;
import prueba.prueba.infrastructure.entity.RefreshTokenEntity;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.repository.SpringUsuarioRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final SpringUsuarioRepository userRepository;
    private final JwtService jwtService;

    private final long refreshTokenDurationSec = 7 * 24 * 60 * 60;

    public RefreshTokenEntity createRefreshToken(Long userId) {

        String token = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plusSeconds(refreshTokenDurationSec);

        RefreshTokenEntity refreshToken = new RefreshTokenEntity(token, userId, expiryDate, false);
        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public RefreshTokenEntity validateRefreshToken(String token) throws Exception {

        if (token == null || token.isEmpty()) {
            throw new BadRequestException("El refresh token no puede ser vacio o nulo");
        }

        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        if (refreshToken.isRevoked()) {
            throw new IllegalArgumentException("Token revocado");
        }

        if (refreshToken.getExpired().isBefore(Instant.now())) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new IllegalArgumentException("El refresh token expiro");
        }

        return refreshToken;
    }

    public String refreshAccessToken(String refreshTokenStr) throws Exception {
        RefreshTokenEntity refreshToken = validateRefreshToken(refreshTokenStr);

        UsuarioEntity userEntity = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        return jwtService.generateToken(userEntity);
    }

    public void revokeByUserId(Long userId) {
        List<RefreshTokenEntity> tokens = refreshTokenRepository.findByUserId(userId);

        tokens.stream()
                .filter(rt -> !rt.isRevoked())
                .forEach(rt -> {
                    rt.setRevoked(true);
                    refreshTokenRepository.save(rt);
                });
    }

    public void revokeByToken(String token) throws Exception {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        if (refreshToken.isRevoked()) {
            throw new IllegalArgumentException("El token ya esta revocado");
        }

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }
}
