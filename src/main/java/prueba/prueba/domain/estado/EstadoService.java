package prueba.prueba.domain.estado;

import prueba.prueba.dto.EstadoDTO;

import java.util.List;

public interface EstadoService {

    EstadoDTO save(EstadoDTO estadoDTO);

    EstadoDTO update(Long id, EstadoDTO estadoDTO);

    EstadoDTO findById(Long id);

    List<EstadoDTO> findAll();

    void delete(Long id);
}
