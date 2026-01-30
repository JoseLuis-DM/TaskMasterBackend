package prueba.prueba.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prueba.prueba.domain.categoria.Categoria;
import prueba.prueba.domain.categoria.CategoriaRepository;
import prueba.prueba.domain.categoria.CategoriaService;
import prueba.prueba.domain.estado.Estado;
import prueba.prueba.dto.CategoriaDTO;
import prueba.prueba.infrastructure.mapper.CategoriaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    @Transactional
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaRepository.guardar(categoriaMapper.dtoToCategoria(categoriaDTO));
        return categoriaMapper.toCategoriaDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaMapper.dtoToCategoria(categoriaDTO);
        categoria.setId(id);
        return categoriaMapper.toCategoriaDTO(categoriaRepository.update(categoria));
    }

    @Override
    @Transactional
    public CategoriaDTO findById(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        return categoriaMapper.toCategoriaDTO(categoria);
    }

    @Override
    @Transactional
    public List<CategoriaDTO> findAll() {

        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toCategoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {

        categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        categoriaRepository.deleteById(id);
    }
}
