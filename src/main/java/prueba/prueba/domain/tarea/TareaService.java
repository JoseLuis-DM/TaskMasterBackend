package prueba.prueba.domain.tarea;

import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;

import java.util.List;

public interface TareaService {

    TareaResponseDTO save(TareaRequestDTO tareaRequestDTO);

    TareaResponseDTO update(Long id, TareaRequestDTO tareaRequestDTO);

    TareaResponseDTO findById(Long id);

    List<TareaResponseDTO> findAll();

    List<TareaResponseDTO> findMe();

    void deleteById(Long id);

    List<TareaResponseDTO> findMyTasks(Long categoriaId, Long estadoId, String search);
}
