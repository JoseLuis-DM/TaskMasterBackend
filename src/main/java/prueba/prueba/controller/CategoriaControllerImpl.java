package prueba.prueba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.prueba.domain.categoria.CategoriaController;
import prueba.prueba.domain.categoria.CategoriaService;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.ApiResponseFactory;
import prueba.prueba.dto.CategoriaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaControllerImpl implements CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaDTO>> register(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoria = categoriaService.save(categoriaDTO);
        return ApiResponseFactory.creado(categoria, "Categoria creada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> update(
            @PathVariable Long id,
            @RequestBody CategoriaDTO categoriaDTO
    ) {
        CategoriaDTO categoria = categoriaService.update(id, categoriaDTO);
        return ApiResponseFactory.exito(categoria, "Categoria actualizada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> findById(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.findById(id);
        return ApiResponseFactory.exito(categoria, "Categoria encontrada");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> findAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return ApiResponseFactory.exito(categorias, "Categorías encontradas");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ApiResponseFactory.exito(null, "Categoria eliminada correctamente");
    }
}
