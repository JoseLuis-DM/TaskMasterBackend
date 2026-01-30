package prueba.prueba.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {

    private boolean exito;

    private String mensaje;

    private T data;

    private LocalDateTime timestamp;
}
