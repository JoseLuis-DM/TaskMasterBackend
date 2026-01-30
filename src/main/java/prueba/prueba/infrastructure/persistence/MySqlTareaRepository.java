package prueba.prueba.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prueba.prueba.domain.tarea.Tarea;
import prueba.prueba.domain.tarea.TareaRepository;
import prueba.prueba.infrastructure.entity.TareaEntity;
import prueba.prueba.infrastructure.mapper.TareaMapper;
import prueba.prueba.infrastructure.repository.SpringTareaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlTareaRepository implements TareaRepository {

    private final SpringTareaRepository springTareaRepository;
    private final TareaMapper tareaMapper;

    @Override
    public Tarea guardar(Tarea tarea) {
        TareaEntity entidad = tareaMapper.toEntity(tarea);
        TareaEntity guardar = springTareaRepository.save(entidad);
        return tareaMapper.toTarea(guardar);
    }

    @Override
    public Optional<Tarea> findById(Long userId) {
        return springTareaRepository.findById(userId)
                .map(tareaMapper::toTarea);
    }

    @Override
    public List<Tarea> findAll() {
        return springTareaRepository.findAll()
                .stream().map(tareaMapper::toTarea).toList();
    }

    @Override
    public void deleteById(Long id) {
        springTareaRepository.deleteById(id);
    }

    @Override
    public Optional<Tarea> findByIdAndUsuarioId(Long id, Long userId) {
        return springTareaRepository
                .findByIdAndUsuario_Id(id, userId)
                .map(tareaMapper::toTarea);
    }
}
