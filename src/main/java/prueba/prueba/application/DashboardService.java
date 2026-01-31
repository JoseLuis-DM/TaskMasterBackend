package prueba.prueba.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prueba.prueba.dto.CategoriaDTO;
import prueba.prueba.dto.DashboardSummaryDTO;
import prueba.prueba.dto.PorcentajeCategoriaDTO;
import prueba.prueba.dto.TareaResponseDTO;
import prueba.prueba.infrastructure.entity.CategoriaEntity;
import prueba.prueba.infrastructure.entity.TareaEntity;
import prueba.prueba.infrastructure.repository.SpringCategoriaRepository;
import prueba.prueba.infrastructure.repository.SpringTareaRepository;
import prueba.prueba.infrastructure.security.SecurityUtils;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DashboardService {

    private final SpringTareaRepository tareaRepository;
    private final SecurityUtils securityUtils;
    private final SpringCategoriaRepository categoriaRepository;

    @Transactional
    public DashboardSummaryDTO getDashboardSummary() {
        Long usuarioId = securityUtils.getCurrentUserId();

        Long totalTareas = tareaRepository.countTotalTareas(usuarioId);
        Long tareasCompletadas = tareaRepository.countTareasCompletadas(usuarioId);
        Long tareasPendientes = tareaRepository.countTareasPendientes(usuarioId);

        double porcentajeCompletadas = totalTareas == 0 ? 0 : (double) tareasCompletadas / totalTareas * 100;
        double porcentajePendientes = totalTareas == 0 ? 0 : (double) tareasPendientes / totalTareas * 100;

        List<Object[]> resultadosPorCategoria = tareaRepository.countTareasPorCategoria(usuarioId);
        List<CategoriaDTO> bloquesPorCategoria = resultadosPorCategoria.stream()
                .map(row -> {
                    String nombre = (String) row[0];
                    Long total = (Long) row[1];

                    Long id = categoriaRepository.findByNombre(nombre)
                            .map(CategoriaEntity::getId)
                            .orElse(null);

                    return CategoriaDTO.builder()
                            .id(id)
                            .nombre(nombre)
                            .totalTareas(total)
                            .build();
                })
                .toList();

        List<PorcentajeCategoriaDTO> porcentajesPorCategoria = bloquesPorCategoria.stream()
                .map(cat -> new PorcentajeCategoriaDTO(
                        cat.getNombre(),
                        totalTareas == 0 ? 0 : (double) cat.getTotalTareas() / totalTareas * 100
                ))
                .toList();

        List<TareaEntity> tareasRecientesEntities = tareaRepository.findTop6ByUsuarioIdOrderByLimiteDesc(usuarioId);
        List<TareaResponseDTO> tareasRecientes = tareasRecientesEntities.stream()
                .map(TareaResponseDTO::fromEntity)
                .toList();

        return DashboardSummaryDTO.builder()
                .totalTareas(totalTareas)
                .tareasCompletadas(tareasCompletadas)
                .tareasPendientes(tareasPendientes)
                .porcentajesCompletadas(porcentajeCompletadas)
                .porcentajesPendientes(porcentajePendientes)
                .bloquesPorCategoria(bloquesPorCategoria)
                .porcentajesPorCategoria(porcentajesPorCategoria)
                .tareasRecientes(tareasRecientes)
                .build();
    }
}
