package co.edu.uniquindio.uniMarket.entidades;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @OneToMany(mappedBy = "usuarioVentas")
    private List<Producto> productosVenta;
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "usuarioFavoritos")
    private List<Producto> favoritosFavoritos;
    @OneToMany(mappedBy = "usuario")
    private List<Compra> productosCompra;
}
