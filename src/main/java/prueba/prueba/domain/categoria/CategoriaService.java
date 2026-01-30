package prueba.prueba.domain.categoria;

import prueba.prueba.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    CategoriaDTO save(CategoriaDTO categoriaDTO);

    CategoriaDTO update(Long id, CategoriaDTO categoriaDTO);

    CategoriaDTO findById(Long id);

    List<CategoriaDTO> findAll();

    void delete(Long id);
}
