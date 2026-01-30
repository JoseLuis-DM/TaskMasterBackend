package prueba.prueba.infrastructure.mapper;

import org.mapstruct.*;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.infrastructure.entity.CategoriaEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface CategoriaMapper {

    Categoria toCategoria(CategoriaEntity categoriaEntity);

    CategoriaEntity toCategoriaEntity(Categoria categoria);

    //Categoria dtoToCategoria(CategoriaDTO categoriaDTO);

    //CategoriaDTO toCategiraDTO(Categoria categoria);

    //CategoriaDTO toCategoriaDTO(CategoriaEntity categoriaEntity);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //void updateToEntity(CategoriaDTO categoriaDTO, @MappingTarget CategoriaEntity categoriaEntity);
}
