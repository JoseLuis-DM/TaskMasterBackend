package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.dto.UsuarioPerfilResponseDTO;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.dto.UsuarioDTO;
import prueba.prueba.dto.auth.RegisterRequest;
import prueba.prueba.infrastructure.entity.UsuarioEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioEntity usuarioEntity);

    UsuarioEntity toUsuarioEntity(Usuario usuario);

    Usuario registerToUsuario(RegisterRequest request);

    Usuario dtoToUsuario(UsuarioDTO usuarioDTO);

    @Mapping(target = "password", ignore = true)
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    @Mapping(target = "password", ignore = true)
    UsuarioDTO toUsuarioDTO(UsuarioEntity usuarioEntity);

    UsuarioPerfilResponseDTO toUsuarioPerfilResponseDTO(UsuarioEntity usuarioEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateToEntity(UsuarioDTO usuarioDTO, @MappingTarget UsuarioEntity usuarioEntity);
}
