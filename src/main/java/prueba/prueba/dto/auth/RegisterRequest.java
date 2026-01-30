package prueba.prueba.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 5)
    private String password;
}
