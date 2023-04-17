package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigoProducto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    // Creo que no es necesario pasarle nada
    List<Producto> listarProductos (String cedulaUsuario);

    // Buscar productos por nombre y/o precio
    List<Producto> buscarProductos(String nombre, Double precio);

    void comentarProducto(String mensaje, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    // Tal vez sea DetalleCompra
    void comprarProductos(Compra compra) throws Exception;

    // Creo que no es necesario pasarle nada
    List<Favorito> listarFavoritos (String cedulaUsuario);
}
