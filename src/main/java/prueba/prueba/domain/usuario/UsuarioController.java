package prueba.prueba.domain.usuario;

import org.springframework.http.ResponseEntity;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioController {

    ResponseEntity<ApiResponse<UsuarioDTO>> register(UsuarioDTO userDTO);

    ResponseEntity<ApiResponse<UsuarioDTO>> update(Long id, UsuarioDTO userDTO);

    ResponseEntity<ApiResponse<UsuarioDTO>> findById(Long id);

    ResponseEntity<ApiResponse<UsuarioDTO>> findMe();

    ResponseEntity<ApiResponse<List<UsuarioDTO>>> findAll();

    ResponseEntity<ApiResponse<Void>> delete(Long id);
}
