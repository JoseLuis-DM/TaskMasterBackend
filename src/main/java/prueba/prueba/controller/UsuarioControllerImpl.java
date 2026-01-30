package prueba.prueba.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.prueba.domain.usuario.UsuarioController;
import prueba.prueba.domain.usuario.UsuarioService;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.ApiResponseFactory;
import prueba.prueba.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> register(@Valid @RequestBody UsuarioDTO userDTO) {
        UsuarioDTO usuarioDTO = usuarioService.save(userDTO);
        return ApiResponseFactory.creado(usuarioDTO, "Usuario creado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO userDTO
    ) {
        try {
            UsuarioDTO updated = usuarioService.update(id, userDTO);
            return ApiResponseFactory.exito(updated, "Usuario actualizado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(dto -> ApiResponseFactory.exito(dto, "Usuario encontrado"))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> findAll() {
        List<UsuarioDTO> usuariosDTO = usuarioService.findAll();
        return ApiResponseFactory.exito(usuariosDTO, "Usuarios encontrados");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ApiResponseFactory.exito(null, "Usuario eliminado correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
