package prueba.prueba.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import prueba.prueba.infrastructure.entity.TareaEntity;

public class TareaSpecifications {

    public static Specification<TareaEntity> byUsuario(Long usuarioId) {
        return (root, query, cb) ->
                cb.equal(root.get("usuario").get("id"), usuarioId);
    }

    public static Specification<TareaEntity> byCategoria(Long categoriaId) {
        return (root, query, cb) ->
                categoriaId == null
                        ? cb.conjunction()
                        : cb.equal(root.get("categoria").get("id"), categoriaId);
    }

    public static Specification<TareaEntity> byEstado(Long estadoId) {
        return (root, query, cb) ->
                estadoId == null
                        ? cb.conjunction()
                        : cb.equal(root.get("estado").get("id"), estadoId);
    }

    public static Specification<TareaEntity> search(String text) {
        return (root, query, cb) -> {
            if (text == null || text.isBlank()) {
                return cb.conjunction();
            }

            String like = "%" + text.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("titulo")), like),
                    cb.like(cb.lower(root.get("descripcion")), like)
            );
        };
    }
}
