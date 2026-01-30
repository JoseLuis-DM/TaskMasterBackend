package prueba.prueba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.prueba.application.AuthenticationService;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.ApiResponseFactory;
import prueba.prueba.dto.auth.*;
import prueba.prueba.infrastructure.mapper.UsuarioMapper;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        Usuario usuario = usuarioMapper.registerToUsuario(request);
        AuthenticationResponse authenticationResponse = authenticationService.register(usuario);
        return ApiResponseFactory.creado(authenticationResponse, "Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponseFactory.exito(response, "Usuario logeado exitosamente");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(@RequestBody RefreshTokenRequest request) throws Exception {
        return ApiResponseFactory.exito(authenticationService.refreshToken(request.getRefreshToken()), "refresh token exitoso");
    }
}
