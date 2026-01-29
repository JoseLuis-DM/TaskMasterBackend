package prueba.prueba.infrastructure.persistence.categoria;

import jakarta.persistence.*;
import lombok.*;
import prueba.prueba.infrastructure.persistence.tarea.TareaEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<TareaEntity> tareas = new ArrayList<>();
}
