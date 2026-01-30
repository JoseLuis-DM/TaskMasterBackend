package prueba.prueba.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.domain.estado.EstadoRepository;
import prueba.prueba.domain.estado.EstadoService;
import prueba.prueba.dto.EstadoDTO;
import prueba.prueba.infrastructure.mapper.EstadoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    @Transactional
    public EstadoDTO save(EstadoDTO estadoDTO) {

        Estado estado = estadoRepository.guardar(estadoMapper.dtoToEstado(estadoDTO));
        return estadoMapper.toEstadoDTO(estado);
    }

    @Override
    @Transactional
    public EstadoDTO update(Long id, EstadoDTO estadoDTO) {

        Estado estado = estadoMapper.dtoToEstado(estadoDTO);
        estado.setId(id);
        return estadoMapper.toEstadoDTO(estadoRepository.update(estado));
    }

    @Override
    @Transactional
    public EstadoDTO findById(Long id) {

        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
        return estadoMapper.toEstadoDTO(estado);
    }

    @Override
    @Transactional
    public List<EstadoDTO> findAll() {

        return estadoRepository.findAll()
                .stream()
                .map(estadoMapper::toEstadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        estadoRepository.deleteById(id);
    }
}
