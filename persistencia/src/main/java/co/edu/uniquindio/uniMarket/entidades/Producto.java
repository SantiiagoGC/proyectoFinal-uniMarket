package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 250)
    private  String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDate fechaCreado;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentariosProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleComprasProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Imagen> imagenesProducto = new ArrayList<>();

    @OneToMany(mappedBy = "productoCategorias")
    @ToString.Exclude
    private List<Categoria> categoriaProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Favorito> favoritoProducto = new ArrayList<>();

    @OneToMany(mappedBy = "productoModerador")
    @ToString.Exclude
    private List<ProductoModerador> moderadorProducto = new ArrayList<>();

    public Producto(String id, String nombre, Integer unidades, String descripcion, Double precio, boolean activo, Usuario vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.vendedor = vendedor;
    }

}
