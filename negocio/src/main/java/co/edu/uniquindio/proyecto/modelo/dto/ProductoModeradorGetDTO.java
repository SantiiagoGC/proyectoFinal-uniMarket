package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoModeradorGetDTO {

    private String motivo;
    private String cedulaModerador;
    private Integer idProducto;
    private String nombreEstado;

}
