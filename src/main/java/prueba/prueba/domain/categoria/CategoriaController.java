package prueba.prueba.domain.categoria;

import org.springframework.http.ResponseEntity;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaController {

    ResponseEntity<ApiResponse<CategoriaDTO>> register(CategoriaDTO categoriaDTO);

    ResponseEntity<ApiResponse<CategoriaDTO>> update(Long id, CategoriaDTO categoriaDTO);

    ResponseEntity<ApiResponse<CategoriaDTO>> findById(Long id);

    ResponseEntity<ApiResponse<List<CategoriaDTO>>> findAll();

    ResponseEntity<ApiResponse<Void>> delete(Long id);
}
