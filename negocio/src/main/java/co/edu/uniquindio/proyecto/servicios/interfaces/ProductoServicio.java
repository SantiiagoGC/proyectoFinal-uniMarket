package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.modelo.dto.ComentarioPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.FavoritoPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoPostDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProductoServicio {

    Integer publicarProducto(ProductoPostDTO producto) throws Exception;

    void eliminarProducto(Integer codigoProducto) throws Exception;

    List<ProductoGetDTO> listarPorEstado (boolean estado);

    void actualizarProducto(Integer codigo, ProductoPostDTO producto) throws Exception;

    ProductoGetDTO obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    // Creo que no es necesario pasarle nada
    List<ProductoGetDTO> listarProducto ();

    List<ProductoGetDTO> listarProductosPersona (Integer cedula);

    List<ProductoGetDTO> listarProductosFavoritos (Integer cedula);

    //Integer comentarProducto(ComentarioPostDTO comentario) throws Exception;

    List<ProductoGetDTO> listarProductosPorRangoPrecio(Double minPrice, Double maxPrice);

    void guardarProductoFavoritos(FavoritoPostDTO favorito) throws Exception;

    void eliminarProductoFavoritos(Integer cedula,Integer codigoProducto) throws Exception;

    // Creo que no es necesario pasarle nada
    List<ProductoGetDTO> listarPorNombreYOPrecio(String nombre, Double precio);

    String obtenerEmailVendedor(Integer id) throws Exception;

    LocalDate obtenerFechaLimite(Integer id) throws Exception;

    List<Integer> listarProductosCategoria(String categoria);

    List<ProductoGetDTO> listarProductosPorCategoria (String categoria);

    int revisarProducto(int idProduct, boolean estado) throws Exception;
}
