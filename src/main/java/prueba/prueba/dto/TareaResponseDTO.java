package prueba.prueba.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import prueba.prueba.infrastructure.entity.TareaEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String estado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate limite;

    public static TareaResponseDTO fromEntity(TareaEntity tarea) {
        return TareaResponseDTO.builder()
                .id(tarea.getId())
                .titulo(tarea.getTitulo())
                .descripcion(tarea.getDescripcion())
                .categoria(tarea.getCategoria().getNombre())
                .estado(tarea.getEstado().getNombre())
                .limite(tarea.getLimite())
                .build();
    }
}
