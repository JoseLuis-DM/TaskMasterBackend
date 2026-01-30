package prueba.prueba.domain.usuario;

import prueba.prueba.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuarioDTO);

    UsuarioDTO update(Long id, UsuarioDTO usuarioDTO);

    Optional<UsuarioDTO> findById(Long id);

    List<UsuarioDTO> findAll();

    void deleteById(Long id);
}
