package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.ComentarioPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/comentar")
    public ResponseEntity<MensajeDTO> createComment(@RequestBody ComentarioPostDTO commentDTO) throws Exception{
        comentarioServicio.crearComentario(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false, "Comentario creado correctamente"));
    }

    @GetMapping("/obtener_comentarios_producto/{idProduct}")
    public ResponseEntity<MensajeDTO> listCommentsByProduct(@PathVariable int idProduct){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, comentarioServicio.listarComentarioProducto(idProduct)));
    }
}
