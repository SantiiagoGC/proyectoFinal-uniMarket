package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraPostDTO {

    @Positive
    @NotNull
    private Double valorTotal;

    @NotBlank
    private String metodoPago;

    @Positive
    @NotNull
    private Integer cedulaUsuario;

}
