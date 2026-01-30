package prueba.prueba.infrastructure.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import prueba.prueba.domain.usuario.Usuario;
import prueba.prueba.dto.auth.RegisterRequest;
import prueba.prueba.infrastructure.entity.UsuarioEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface UsuarioMapper {

    @Mapping(target = "tareas", ignore = true)
    Usuario toUsuario(UsuarioEntity usuarioEntity);

    UsuarioEntity toUsuarioEntity(Usuario usuario);

    Usuario registerToUsuario(RegisterRequest request);
}
