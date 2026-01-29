package prueba.prueba.infrastructure.persistence.tarea;

import jakarta.persistence.*;
import lombok.*;
import prueba.prueba.infrastructure.persistence.categoria.CategoriaEntity;
import prueba.prueba.infrastructure.persistence.estado.EstadoEntity;
import prueba.prueba.infrastructure.persistence.usuario.UsuarioEntity;

@Entity
@Table(name = "tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEntity estado;
}
