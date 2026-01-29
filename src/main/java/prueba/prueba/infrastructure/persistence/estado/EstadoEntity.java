package prueba.prueba.infrastructure.persistence.estado;

import jakarta.persistence.*;
import lombok.*;
import prueba.prueba.infrastructure.persistence.tarea.TareaEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<TareaEntity> tareas = new ArrayList<>();
}
