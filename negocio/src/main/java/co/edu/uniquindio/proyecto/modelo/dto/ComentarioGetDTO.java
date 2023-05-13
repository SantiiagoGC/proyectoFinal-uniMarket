package co.edu.uniquindio.proyecto.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ComentarioGetDTO {

    private int id;
    private LocalDate fechaComentario;
    private String mensaje;
    private int idProducto;
    private int cedulaPersona;

}
