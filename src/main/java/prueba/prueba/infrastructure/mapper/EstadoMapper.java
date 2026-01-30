package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.infrastructure.entity.EstadoEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface EstadoMapper {

    Estado toEstado(EstadoEntity estadoEntity);

    EstadoEntity toEstadoEntity(Estado estado);

    //Estado dtoToEstado(CategoriaDTO categoriaDTO);

    //EstadoDTO toEstadoDTO(Estado estado);

    //EstadoDTO toEstadoDTO(EstadoEntity estadoEntity);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //void updateToEntity(EstadoDTO estadoDTO, @MappingTarget EstadoEntity estadoEntity);
}
