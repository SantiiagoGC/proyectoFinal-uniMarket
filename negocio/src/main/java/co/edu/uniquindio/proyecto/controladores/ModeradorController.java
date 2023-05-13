package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/moderador")
public class ModeradorController {

    private final ProductoServicio productoServicio;

    @GetMapping("/obtener_productos_estado/{estadoProducto}")
    public ResponseEntity<MensajeDTO> listProductByState(@PathVariable boolean estadoProducto){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarPorEstado(estadoProducto)));
    }

    @PutMapping("/revisar_producto/{idProduct}/{estadoProduct}")
    public ResponseEntity<MensajeDTO> reviewProduct(@PathVariable int idProduct, @PathVariable boolean estadoProduct) throws Exception {
        productoServicio.revisarProducto(idProduct, estadoProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Producto revisado correctamente"));
    }
}
