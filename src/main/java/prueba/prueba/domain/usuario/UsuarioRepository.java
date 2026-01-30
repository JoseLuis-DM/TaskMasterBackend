package prueba.prueba.domain.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Long userId);

    List<Usuario> findAll();

    void deleteById(Long id);
}
