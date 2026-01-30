package prueba.prueba.domain.estado;

import org.springframework.http.ResponseEntity;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.EstadoDTO;

import java.util.List;

public interface EstadoController {

    ResponseEntity<ApiResponse<EstadoDTO>> register(EstadoDTO estadoDTO);

    ResponseEntity<ApiResponse<EstadoDTO>> update(Long id, EstadoDTO estadoDTO);

    ResponseEntity<ApiResponse<EstadoDTO>> findById(Long id);

    ResponseEntity<ApiResponse<List<EstadoDTO>>> findAll();

    ResponseEntity<ApiResponse<Void>> delete(Long id);
}
