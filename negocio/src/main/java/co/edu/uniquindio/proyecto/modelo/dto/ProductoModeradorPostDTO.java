package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductoModeradorPostDTO {

    private String motivo;
    private String cedulaModerador;
    private Integer idProducto;
    private String nombreEstado;

}
