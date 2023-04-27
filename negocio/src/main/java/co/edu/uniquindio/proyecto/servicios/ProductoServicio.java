package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigoProducto) throws Exception;

    Producto actualizarProducto(Producto producto) throws Exception;

    Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException;

    // Creo que no es necesario pasarle nada
    //List<Producto> listarProductosCedula (String cedulaUsuario);

    // Buscar productos por nombre y/o precio

    void comentarProducto(String mensaje, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    // Tal vez sea DetalleCompra
    void comprarProductos(Compra compra) throws Exception;


    //Consultar el email
    String obtenerEmailVendedor(Integer id) throws Exception;

    LocalDate obtenerFechaLimite(Integer id) throws Exception;

    List<Categoria> listarProductosCategoria(String categoria) throws Exception;

    // Creo que no es necesario pasarle nada
    List<Favorito> listarFavoritos (String cedulaUsuario);

    List<Producto> listarPorNombreYOPrecio(String nombre, Double precio);

    List<Producto> listarProducto() throws Exception;


}
