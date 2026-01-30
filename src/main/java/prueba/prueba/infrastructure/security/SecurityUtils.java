package prueba.prueba.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.repository.SpringUsuarioRepository;

@Component
public class SecurityUtils {

    private final SpringUsuarioRepository usuarioRepository;

    public SecurityUtils(SpringUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("Usuario no autenticado");
        }

        // auth.getName() devuelve el username/email del JWT
        String username = auth.getName();

        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}




