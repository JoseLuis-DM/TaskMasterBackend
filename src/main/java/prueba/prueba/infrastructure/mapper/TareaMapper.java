package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.tarea.Tarea;
import prueba.prueba.dto.TareaRequestDTO;
import prueba.prueba.dto.TareaResponseDTO;
import prueba.prueba.infrastructure.entity.TareaEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface TareaMapper {

    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "estado", source = "estado")
    Tarea toTarea(TareaEntity entity);

    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "estado", source = "estado")
    TareaEntity toEntity(Tarea tarea);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true) // se asigna en el service
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Tarea fromRequest(TareaRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(TareaRequestDTO dto, @MappingTarget Tarea tarea);

    TareaResponseDTO domainToResponse(Tarea tarea);
}
