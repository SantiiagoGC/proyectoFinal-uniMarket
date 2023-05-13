package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.FavoritoPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoPostDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ProductoController {

    private final ProductoServicio productoServicio;

    @Operation(summary = "Registrar producto", description = "Publicar producto dado los datos del mismo.")
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> registrar(@Valid @RequestBody ProductoPostDTO producto) throws Exception{
        productoServicio.publicarProducto(producto);
        return ResponseEntity.status(201).body(new MensajeDTO(HttpStatus.CREATED, false,
                "Producto creado correctamente"));
    }

    @Operation(summary = "Actualizar producto", description = "Actualizar producto por su ID y sus nuevos datos")
    @PutMapping("/actualizar/{idProduct}")
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable int idProduct, @RequestBody ProductoPostDTO productoPostDTO) throws Exception{
        productoServicio.actualizarProducto(idProduct, productoPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Producto actualizado correctamente"));

    }

    @Operation(summary = "Eliminar producto", description = "Eliminar producto dado su ID")
    @PutMapping("/eliminar/{idProduct}")
    public ResponseEntity<MensajeDTO> eliminarProducto(@PathVariable int idProduct) throws Exception{
        productoServicio.eliminarProducto(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Producto eliminado correctamente"));
    }

    @Operation(summary = "Obtener producto", description = "Obtener cierto producto por la su ID")
    @GetMapping("/obtener/{idProduct}")
    public ResponseEntity<MensajeDTO> obtenerProducto (@PathVariable int idProduct) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.obtenerProducto(idProduct)));
    }

    @Operation(summary = "Añadir producto a favoritos", description = "Añadir producto de la lista de favoritos de cierto Usuario")
    @PostMapping("/agregar_producto_favorito")
    public ResponseEntity<MensajeDTO> addFavoriteProduct(@RequestBody FavoritoPostDTO favoritoPostDTO) throws Exception{
        productoServicio.guardarProductoFavoritos(favoritoPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Producto agregado correctamente a favoritos"));
    }

    @Operation(summary = "Eliminar producto de favoritos", description = "Eliminar producto de la lista de favoritos de cierto Usuario, dado su cedula y la ID del producto")
    @DeleteMapping("/quitar_producto_favorito/{cedula}/{idProduct}")
    public ResponseEntity<MensajeDTO> removeFavoriteProduct(@PathVariable int cedula, @PathVariable int idProduct) throws Exception{
        productoServicio.eliminarProductoFavoritos(cedula,idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Producto eliminado correctamente de favoritos"));
    }

    @Operation(summary = "Listar productos By Nombre y Precio", description = "Listar productos por cierto NOMBRE y cierto PRECIO")
    @GetMapping("/obtener_productos_filtroNP/{nombre}/{precio}")
    public ResponseEntity<MensajeDTO> listarProductosPorNombreYOPrecio(@PathVariable String nombre, @PathVariable double precio){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarPorNombreYOPrecio(nombre, precio)));
    }

    @GetMapping("/obtener_productos_categoria/{idCategory}")
    public List<ProductoGetDTO> listarProductosPorCategoria(@PathVariable String idCategory){
        return productoServicio.listarProductosPorCategoria(idCategory);
    }

    @GetMapping("/obtener_productos_persona/{cedula}")
    public List<ProductoGetDTO> listarProductorPorUsuario(@PathVariable int cedula){
        return productoServicio.listarProductosPersona(cedula);
    }

    @GetMapping("/obtener_favoritos_persona/{cedula}")
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(@PathVariable int cedula){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosFavoritos(cedula)));
    }

    @GetMapping("/obtener_productos_precio/{minPrice}/{maxPrice}")
    public ResponseEntity<MensajeDTO> listarProductosPorPrecio(@PathVariable double minPrice,@PathVariable double maxPrice){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosPorRangoPrecio(minPrice, maxPrice)));
    }

}
