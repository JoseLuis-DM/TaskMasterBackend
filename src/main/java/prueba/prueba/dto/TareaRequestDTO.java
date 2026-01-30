package prueba.prueba.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaRequestDTO {

    private String titulo;
    private String descripcion;
    private Long categoriaId;
    private Long estadoId;
}
