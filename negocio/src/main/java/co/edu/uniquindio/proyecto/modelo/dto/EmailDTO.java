package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmailDTO {
    private String asunto;
    private String cuerpo;
    private String destinatario;
}
