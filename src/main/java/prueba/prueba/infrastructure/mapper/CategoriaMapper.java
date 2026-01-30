package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.dto.CategoriaDTO;
import prueba.prueba.dto.EstadoDTO;
import prueba.prueba.infrastructure.entity.CategoriaEntity;
import prueba.prueba.infrastructure.entity.EstadoEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface CategoriaMapper {

    Categoria toCategoria(CategoriaEntity categoriaEntity);

    CategoriaEntity toCategoriaEntity(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomainToEntity(Categoria categoria, @MappingTarget CategoriaEntity entity);

    Categoria dtoToCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO toCategoriaDTO(Categoria categoria);
}
