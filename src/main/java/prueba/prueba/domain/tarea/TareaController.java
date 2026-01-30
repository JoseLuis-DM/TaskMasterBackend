package prueba.prueba.domain.tarea;

import org.springframework.http.ResponseEntity;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;

import java.util.List;

public interface TareaController {

    ResponseEntity<ApiResponse<TareaResponseDTO>> register(TareaRequestDTO tareaRequestDTO);

    ResponseEntity<ApiResponse<TareaResponseDTO>> update(Long id, TareaRequestDTO tareaRequestDTO);

    ResponseEntity<ApiResponse<TareaResponseDTO>> findById(Long id);

    ResponseEntity<ApiResponse<List<TareaResponseDTO>>> findAll();

    ResponseEntity<ApiResponse<List<TareaResponseDTO>>> findMe();

    ResponseEntity<ApiResponse<Void>> delete(Long id);
}
