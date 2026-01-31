package prueba.prueba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import prueba.prueba.domain.estado.EstadoController;
import prueba.prueba.domain.estado.EstadoService;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.ApiResponseFactory;
import prueba.prueba.dto.EstadoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
public class EstadoControllerImpl implements EstadoController {

    private final EstadoService estadoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<EstadoDTO>> register(@RequestBody EstadoDTO estadoDTO) {
        EstadoDTO estado = estadoService.save(estadoDTO);
        return ApiResponseFactory.creado(estado, "Estado creado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoDTO>> update(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO) {
        EstadoDTO estado = estadoService.update(id, estadoDTO);
        return ApiResponseFactory.exito(estado, "Estado actualizado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoDTO>> findById(@PathVariable Long id) {
        EstadoDTO estado = estadoService.findById(id);
        return ApiResponseFactory.exito(estado, "Estado encontrado");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadoDTO>>> findAll() {
        List<EstadoDTO> estados = estadoService.findAll();
        return ApiResponseFactory.exito(estados, "Estados encontrados");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        estadoService.delete(id);
        return ApiResponseFactory.exito(null, "Estado eliminado correctamente");
    }
}