package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraPostDTO {

    private Double valorTotal;
    private String metodoPago;
    private String cedulaUsuario;
    private List<Integer> idsDetalleCompra;

}
