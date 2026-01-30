package prueba.prueba.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ApiResponseFactory {

    public static <T>ResponseEntity<ApiResponse<T>> exito(T data, String mensaje) {
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<T>builder()
                                .exito(true)
                                .mensaje(mensaje)
                                .data(data)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    public static <T>ResponseEntity<ApiResponse<T>> creado(T data, String mensaje) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<T>builder()
                                .exito(true)
                                .mensaje(mensaje)
                                .data(data)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    public static <T>ResponseEntity<ApiResponse<T>> noContenido(T data, String mensaje) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(
                        ApiResponse.<T>builder()
                                .exito(true)
                                .mensaje(mensaje)
                                .data(data)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    public static <T>ResponseEntity<ApiResponse<T>> error(HttpStatus status, String mensaje) {
        return ResponseEntity.status(status)
                .body(
                        ApiResponse.<T>builder()
                                .exito(true)
                                .mensaje(mensaje)
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}
