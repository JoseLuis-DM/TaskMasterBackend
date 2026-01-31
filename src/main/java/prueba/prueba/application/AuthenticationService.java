package prueba.prueba.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.usuario.Rol;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.domain.usuario.UsuarioRepository;
import prueba.prueba.dto.auth.AuthenticationRequest;
import prueba.prueba.dto.auth.AuthenticationResponse;
import prueba.prueba.dto.auth.RefreshTokenResponse;
import prueba.prueba.infrastructure.entity.RefreshTokenEntity;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.mapper.UsuarioMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationResponse register(Usuario usuario, boolean admin) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (admin) {
            usuario.setRol(Rol.ADMIN);
        } else {
            usuario.setRol(Rol.USER);
        }

        Usuario guardado = usuarioRepository.guardar(usuario);

        UsuarioEntity entidad = usuarioMapper.toUsuarioEntity(guardado);

        String jwtToken = jwtService.generateToken(entidad);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String jwtToken = jwtService.generateToken(usuarioMapper.toUsuarioEntity(usuario));

        String refreshToken = refreshTokenService.createRefreshToken(usuario.getId()).getToken();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public RefreshTokenResponse refreshToken(String oldRefreshToken) throws Exception {
        RefreshTokenEntity oldToken = refreshTokenService.validateRefreshToken(oldRefreshToken);
        refreshTokenService.revokeByToken(oldToken.getToken());

        RefreshTokenEntity newToken = refreshTokenService.createRefreshToken(oldToken.getUserId());

        Usuario usuario = usuarioRepository.findById(oldToken.getUserId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        String newAccessToken = jwtService.generateToken(usuarioMapper.toUsuarioEntity(usuario));

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newToken.getToken())
                .build();
    }
}
