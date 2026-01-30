package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.dto.EstadoDTO;
import prueba.prueba.infrastructure.entity.EstadoEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface EstadoMapper {

    Estado toEstado(EstadoEntity estadoEntity);

    EstadoEntity toEstadoEntity(Estado estado);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomainToEntity(Estado estado, @MappingTarget EstadoEntity entity);

    Estado dtoToEstado(EstadoDTO estadoDTO);

    EstadoDTO toEstadoDTO(Estado estado);
}
