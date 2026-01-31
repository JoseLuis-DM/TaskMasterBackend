package prueba.prueba.infrastructure.boostrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.domain.usuario.Rol;
import prueba.prueba.infrastructure.repository.SpringUsuarioRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final SpringUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        boolean adminExists = usuarioRepository.existsByRol(Rol.ADMIN);
        if (!adminExists) {
            UsuarioEntity admin = UsuarioEntity.builder()
                    .nombre("Admin")
                    .apellidos("Inicial")
                    .email("admin@empresa.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.ADMIN)
                    .build();
            usuarioRepository.save(admin);
            System.out.println("Admin inicial creado: admin@empresa.com / admin123");
        }
    }
}