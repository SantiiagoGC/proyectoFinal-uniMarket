package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FavoritoPostDTO {

    @Positive
    @NotNull
    Integer codigoProducto;

    @Positive
    @NotNull
    Integer cedulaUsuario;
}
