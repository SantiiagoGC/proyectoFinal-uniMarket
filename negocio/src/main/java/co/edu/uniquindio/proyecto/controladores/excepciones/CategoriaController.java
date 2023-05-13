package co.edu.uniquindio.proyecto.controladores.excepciones;


import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaServicio categoriaServicio;

    @GetMapping("/obtener")
    public ResponseEntity<MensajeDTO> listAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, categoriaServicio.listarCategorias()));
    }
}
