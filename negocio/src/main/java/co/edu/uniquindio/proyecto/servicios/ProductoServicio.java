package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigoProducto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    // Creo que no es necesario pasarle nada
    List<Producto> listarProductos (String cedulaUsuario);

    // Buscar productos por nombre y/o precio
    List<Producto> buscarProductos(String nombre, Double precio);

    Comentario comentarProducto(Comentario comentario) throws Exception;

    Favorito guardarProductoFavoritos(Favorito favorito) throws Exception;

    void eliminarProductoFavoritos(Favorito favorito) throws Exception;

    // Tal vez sea DetalleCompra
    void comprarProductos(Compra compra) throws Exception;

    // Creo que no es necesario pasarle nada
    List<Favorito> listarFavoritos (String cedulaUsuario);

    List<Producto> listarPorNombreYOPrecio(String nombre, Double precio);
}
