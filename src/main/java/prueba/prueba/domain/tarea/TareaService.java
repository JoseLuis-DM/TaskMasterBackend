package prueba.prueba.domain.tarea;

import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    TareaResponseDTO save(TareaRequestDTO tareaRequestDTO);

    TareaResponseDTO update(Long id, TareaRequestDTO tareaRequestDTO);

    TareaResponseDTO findById(Long id);

    List<TareaResponseDTO> findAll();

    void deleteById(Long id);
}
