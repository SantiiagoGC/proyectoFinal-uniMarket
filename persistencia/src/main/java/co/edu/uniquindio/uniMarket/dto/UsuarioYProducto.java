package co.edu.uniquindio.uniMarket.dto;

import co.edu.uniquindio.uniMarket.entidades.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UsuarioYProducto {

    private String corre;

    private String nombre;

    private Producto producto;

}
