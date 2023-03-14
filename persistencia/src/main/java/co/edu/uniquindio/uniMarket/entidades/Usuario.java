package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Usuario extends Persona implements Serializable {
    @ManyToMany (mappedBy = "usuarioVentas")
    private List<Producto> productosVentas;

    @ManyToMany(mappedBy = "usuarioFavoritos")
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> productosCompra;
}
