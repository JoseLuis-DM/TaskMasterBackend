package prueba.prueba.infrastructure.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.domain.categoria.CategoriaRepository;
import prueba.prueba.infrastructure.entity.CategoriaEntity;
import prueba.prueba.infrastructure.mapper.CategoriaMapper;
import prueba.prueba.infrastructure.repository.SpringCategoriaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MySqlCategoriaRepository implements CategoriaRepository {

    private final SpringCategoriaRepository springCategoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public Categoria guardar(Categoria categoria) {
        CategoriaEntity entidad = categoriaMapper.toCategoriaEntity(categoria);
        CategoriaEntity guardar = springCategoriaRepository.save(entidad);
        return categoriaMapper.toCategoria(guardar);
    }

    @Override
    public Categoria update(Categoria categoria) {

        CategoriaEntity entity = springCategoriaRepository.findById(categoria.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        categoriaMapper.updateDomainToEntity(categoria, entity);
        CategoriaEntity updated = springCategoriaRepository.save(entity);
        return categoriaMapper.toCategoria(updated);
    }

    @Override
    public Optional<Categoria> findById(Long userId) {
        return springCategoriaRepository.findById(userId)
                .map(categoriaMapper::toCategoria);
    }

    @Override
    public List<Categoria> findAll() {
        return springCategoriaRepository.findAll()
                .stream().map(categoriaMapper::toCategoria).toList();
    }

    @Override
    public void deleteById(Long id) {
        springCategoriaRepository.deleteById(id);
    }
}
