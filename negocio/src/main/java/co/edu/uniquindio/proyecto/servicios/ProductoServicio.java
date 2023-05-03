package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoPostDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProductoServicio {

    int publicarProducto(ProductoPostDTO producto) throws Exception;

    void eliminarProducto(Integer codigoProducto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    // Creo que no es necesario pasarle nada
    List<Producto> listarProducto () throws Exception;

    // Buscar productos por nombre y/o precio
    Comentario comentarProducto(Comentario comentario) throws Exception;

    Favorito guardarProductoFavoritos(Favorito favorito) throws Exception;

    void eliminarProductoFavoritos(Favorito favorito) throws Exception;

    // Creo que no es necesario pasarle nada
    List<Producto> listarPorNombreYOPrecio(String nombre, Double precio);

    String obtenerEmailVendedor(Integer id) throws Exception;

    LocalDate obtenerFechaLimite(Integer id) throws Exception;

    List<Categoria> listarProductosCategoria(String categoria) throws Exception;

}
