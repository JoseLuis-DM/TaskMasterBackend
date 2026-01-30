package prueba.prueba.infrastructure.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.domain.estado.EstadoRepository;
import prueba.prueba.infrastructure.entity.EstadoEntity;
import prueba.prueba.infrastructure.mapper.EstadoMapper;
import prueba.prueba.infrastructure.repository.SpringEstadoRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlEstadoRepository implements EstadoRepository {

    private final SpringEstadoRepository springEstadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    public Estado guardar(Estado estado) {
        EstadoEntity entidad = estadoMapper.toEstadoEntity(estado);
        EstadoEntity guardar = springEstadoRepository.save(entidad);
        return estadoMapper.toEstado(guardar);
    }

    @Override
    public Estado update(Estado estado) {
        EstadoEntity entity = springEstadoRepository.findById(estado.getId())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));

        estadoMapper.updateDomainToEntity(estado, entity);
        EstadoEntity updated = springEstadoRepository.save(entity);
        return estadoMapper.toEstado(updated);
    }

    @Override
    public Optional<Estado> findById(Long userId) {
        return springEstadoRepository.findById(userId)
                .map(estadoMapper::toEstado);
    }

    @Override
    public List<Estado> findAll() {
        return springEstadoRepository.findAll()
                .stream().map(estadoMapper::toEstado).toList();
    }

    @Override
    public void deleteById(Long id) {
        springEstadoRepository.deleteById(id);
    }
}