package prueba.prueba.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.domain.categoria.CategoriaRepository;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.domain.estado.EstadoNombre;
import prueba.prueba.domain.estado.EstadoRepository;
import prueba.prueba.domain.tarea.Tarea;
import prueba.prueba.domain.tarea.TareaRepository;
import prueba.prueba.domain.tarea.TareaService;
import prueba.prueba.domain.usuario.Rol;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.domain.usuario.UsuarioRepository;
import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;
import prueba.prueba.infrastructure.entity.EstadoEntity;
import prueba.prueba.infrastructure.entity.TareaEntity;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.mapper.EstadoMapper;
import prueba.prueba.infrastructure.mapper.TareaMapper;
import prueba.prueba.infrastructure.repository.SpringEstadoRepository;
import prueba.prueba.infrastructure.repository.SpringTareaRepository;
import prueba.prueba.infrastructure.security.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final SpringTareaRepository springTareaRepository;
    private final TareaMapper tareaMapper;
    private final CategoriaRepository categoriaRepository;
    private final EstadoRepository estadoRepository;
    private final SecurityUtils securityUtils;
    private final SpringEstadoRepository springEstadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    @Transactional
    public TareaResponseDTO save(TareaRequestDTO tareaRequestDTO) {

        Tarea tarea = tareaMapper.fromRequest(tareaRequestDTO);

        UsuarioEntity usuarioEntity = securityUtils.getCurrentUser();

        Usuario usuarioDomain = Usuario.builder()
                .id(usuarioEntity.getId())
                .nombre(usuarioEntity.getNombre())
                .apellidos(usuarioEntity.getApellidos())
                .email(usuarioEntity.getEmail())
                .rol(usuarioEntity.getRol())
                .build();

        tarea.setUsuario(usuarioDomain);

        if (tareaRequestDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(tareaRequestDTO.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
            tarea.setCategoria(categoria);
        }

        Estado estado = estadoRepository.findById(tareaRequestDTO.getEstadoId())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
        tarea.setEstado(estado);

        TareaEntity savedEntity = springTareaRepository.save(tareaMapper.toEntity(tarea));

        Tarea savedDomain = tareaMapper.toTarea(savedEntity);

        return tareaMapper.domainToResponse(savedDomain);
    }

    @Transactional
    @Override
    public TareaResponseDTO update(Long id, TareaRequestDTO tareaRequestDTO) {

        UsuarioEntity usuarioEntity = securityUtils.getCurrentUser();

        TareaEntity existingEntity = springTareaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));

        if (!existingEntity.getUsuario().getId().equals(usuarioEntity.getId())) {
            throw new AccessDeniedException("No puedes modificar tareas de otro usuario");
        }

        Tarea existingDomain = tareaMapper.toTarea(existingEntity);

        tareaMapper.updateFromDto(tareaRequestDTO, existingDomain);

        if (tareaRequestDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(tareaRequestDTO.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
            existingDomain.setCategoria(categoria);
        }

        if (tareaRequestDTO.getEstadoId() != null) {
            Estado estado = estadoRepository.findById(tareaRequestDTO.getEstadoId())
                    .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
            existingDomain.setEstado(estado);
        }

        TareaEntity updatedEntity = springTareaRepository.save(
                tareaMapper.toEntity(existingDomain)
        );

        return tareaMapper.domainToResponse(
                tareaMapper.toTarea(updatedEntity)
        );
    }

    @Transactional
    public TareaResponseDTO findById(Long id) {

        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));
        return tareaMapper.domainToResponse(tarea);
    }

    @Transactional
    public List<TareaResponseDTO> findAll() {

        return tareaRepository.findAll()
                .stream()
                .map(tareaMapper::domainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TareaResponseDTO> findMe() {

        UsuarioEntity usuario = securityUtils.getCurrentUser();

        return tareaRepository.findAllByUsuarioId(usuario.getId())
                .stream()
                .map(tareaMapper::domainToResponse)
                .toList();
    }

    @Transactional
    public void deleteById(Long id) {

        tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        tareaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<TareaResponseDTO> findMyTasks(Long categoriaId, Long estadoId, String search) {

        UsuarioEntity usuario = securityUtils.getCurrentUser();

        return tareaRepository.findByFilters(
                        usuario.getId(),
                        categoriaId,
                        estadoId,
                        search
                )
                .stream()
                .map(tareaMapper::domainToResponse)
                .toList();
    }

    @Transactional
    public TareaResponseDTO cambiarEstado(Long tareaId, String nuevoEstadoStr) {

        UsuarioEntity currentUser = securityUtils.getCurrentUser();

        TareaEntity tarea = springTareaRepository.findById(tareaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));

        if (!tarea.getUsuario().getId().equals(currentUser.getId())
                && !currentUser.getRol().equals(Rol.ADMIN)) {
            throw new AccessDeniedException("No tienes permiso para cambiar esta tarea");
        }

        EstadoNombre estadoEnum;
        try {
            estadoEnum = EstadoNombre.valueOf(nuevoEstadoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + nuevoEstadoStr);
        }

        EstadoEntity nuevoEstado = springEstadoRepository.findByNombre(estadoEnum.name())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado en DB"));

        tarea.setEstado(nuevoEstado);

        tareaRepository.guardar(tareaMapper.toTarea(tarea));

        return tareaMapper.domainToResponse(tareaMapper.toTarea(tarea));
    }
}