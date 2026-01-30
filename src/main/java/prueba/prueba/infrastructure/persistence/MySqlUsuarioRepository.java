package prueba.prueba.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.domain.usuario.UsuarioRepository;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.mapper.UsuarioMapper;
import prueba.prueba.infrastructure.repository.SpringUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlUsuarioRepository implements UsuarioRepository {

    private final SpringUsuarioRepository springUsuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entidad = usuarioMapper.toUsuarioEntity(usuario);
        UsuarioEntity guardar = springUsuarioRepository.save(entidad);
        return usuarioMapper.toUsuario(guardar);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return springUsuarioRepository.findByEmail(email)
                .map(usuarioMapper::toUsuario);
    }

    @Override
    public Optional<Usuario> findById(Long userId) {
        return springUsuarioRepository.findById(userId)
                .map(usuarioMapper::toUsuario);
    }

    @Override
    public List<Usuario> findAll() {
        return springUsuarioRepository.findAll()
                .stream().map(usuarioMapper::toUsuario).toList();
    }

    @Override
    public void deleteById(Long id) {
        springUsuarioRepository.deleteById(id);
    }
}
