package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {
    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private String rutaImagen;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, length = 200)
    private  String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Positive
    @Column(nullable = false)
    private boolean disponibilidad;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios;

    @ElementCollection
    private List<Double> notas;

    @Positive
    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioVentas;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioFavoritos;

    @OneToMany(mappedBy = "producto")
    private List<Compra> compras;
}
