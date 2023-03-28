package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {
    @Column(nullable = false)
    private  String direccion;

    @Column(nullable = false, length = 10)
    private  String telefono;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productosVenta = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Compra> productoCompras = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Favorito>  productosFavoritos = new ArrayList<>();

    public Usuario(String cedula, String nombreUsuario, String nombre, String email, String password, String direccion, String telefono) {
        super(cedula, nombreUsuario, nombre, email, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
