package prueba.prueba.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DashboardSummaryDTO(

        Long totalTareas,
        Long tareasCompletadas,
        Long tareasPendientes,
        Double porcentajesCompletadas,
        Double porcentajesPendientes,
        List<CategoriaDTO> bloquesPorCategoria,
        List<TareaResponseDTO> tareasRecientes,
        List<PorcentajeCategoriaDTO> porcentajesPorCategoria
) {}