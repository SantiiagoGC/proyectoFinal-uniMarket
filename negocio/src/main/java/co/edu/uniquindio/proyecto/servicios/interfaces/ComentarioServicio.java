package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.dto.ComentarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ComentarioPostDTO;

import java.util.List;

public interface ComentarioServicio {

    int crearComentario(ComentarioPostDTO comentarioPostDTO) throws Exception;

    List<ComentarioGetDTO> listarComentarioProducto(int idProduct);

}
