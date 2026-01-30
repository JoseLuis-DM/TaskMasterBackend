package prueba.prueba.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.domain.usuario.UsuarioRepository;
import prueba.prueba.domain.usuario.UsuarioService;
import prueba.prueba.dto.UsuarioDTO;
import prueba.prueba.infrastructure.entity.UsuarioEntity;
import prueba.prueba.infrastructure.mapper.UsuarioMapper;
import prueba.prueba.infrastructure.repository.SpringUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final SpringUsuarioRepository springUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioMapper.dtoToUsuario(usuarioDTO);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Usuario saved = usuarioRepository.guardar(usuario);

        return usuarioMapper.toUsuarioDTO(saved);
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {

        UsuarioEntity entity = springUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isBlank()) {
            usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }

        usuarioMapper.updateToEntity(usuarioDTO, entity);

        UsuarioEntity saved = springUsuarioRepository.save(entity);

        return usuarioMapper.toUsuarioDTO(saved);
    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {

        return usuarioRepository.findById(id)
                .map(usuarioMapper::toUsuarioDTO);
    }

    @Override
    public List<UsuarioDTO> findAll() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioDTO)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        usuarioRepository.deleteById(id);
    }
}
