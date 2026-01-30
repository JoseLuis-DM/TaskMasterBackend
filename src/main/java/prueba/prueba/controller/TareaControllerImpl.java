package prueba.prueba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.prueba.domain.tarea.TareaController;
import prueba.prueba.domain.tarea.TareaService;
import prueba.prueba.dto.ApiResponse;
import prueba.prueba.dto.ApiResponseFactory;
import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaControllerImpl implements TareaController {

    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<ApiResponse<TareaResponseDTO>> register(@RequestBody TareaRequestDTO tareaRequestDTO) {
        TareaResponseDTO tarea = tareaService.save(tareaRequestDTO);
        return ApiResponseFactory.creado(tarea, "Tarea creada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TareaResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody TareaRequestDTO tareaRequestDTO
    ) {
        TareaResponseDTO tarea = tareaService.update(id, tareaRequestDTO);
        return ApiResponseFactory.exito(tarea, "Tarea actualizada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TareaResponseDTO>> findById(@PathVariable Long id) {
        TareaResponseDTO tarea = tareaService.findById(id);
        return ApiResponseFactory.exito(tarea, "Tarea encontrada");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TareaResponseDTO>>> findAll() {
        List<TareaResponseDTO> tareas = tareaService.findAll();
        return ApiResponseFactory.exito(tareas, "Tareas encontradas");
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<TareaResponseDTO>>> findMe() {
        return ApiResponseFactory.exito(tareaService.findMe(), "Tareas del usuario obtenidas correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        tareaService.deleteById(id);
        return ApiResponseFactory.exito(null, "Tarea eliminada correctamente");
    }
}
