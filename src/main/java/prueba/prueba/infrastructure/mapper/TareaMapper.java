package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.tarea.Tarea;
import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;
import prueba.prueba.infrastructure.entity.TareaEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                UsuarioMapper.class,
                CategoriaMapper.class,
                EstadoMapper.class
        },
        builder = @Builder(disableBuilder = true)
)
public interface TareaMapper {


    Tarea toTarea(TareaEntity entity);

    TareaEntity toEntity(Tarea tarea);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Tarea fromRequest(TareaRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(TareaRequestDTO dto, @MappingTarget Tarea tarea);

    @Mapping(target = "categoria", source = "categoria.nombre")
    @Mapping(target = "estado", source = "estado.nombre")
    TareaResponseDTO domainToResponse(Tarea tarea);
}
