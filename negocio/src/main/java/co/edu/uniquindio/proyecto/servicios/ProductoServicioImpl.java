package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
        try{
            return productoRepo.save(producto);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // Eliminar cuenta tendria que eliminar primero los comentarios de ahi los productos de ahi si el usuario
    // Eliminacion en cascada soluciona eso
    @Override
    public void eliminarProducto(Integer codigoProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if( producto.isEmpty() ){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigoProducto);
    }
    @Override
    public void actualizarProducto(Producto producto) throws Exception {

    }

    @Override
    public Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        return null;
    }

    @Override
    public String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        return producto.get().getDescripcion();
    }


    @Override
    public List<Producto> listarProductos(String cedulaUsuario) {
        return null;
    }

    @Override
    public List<Producto> buscarProductos(String nombre, Double precio) {
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Usuario usuario, Producto producto) throws Exception {

    }

    @Override
    public void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Favorito> listarFavoritos(String cedulaUsuario) {
        return null;
    }

    @Override
    public List<Producto> listarPorNombreYOPrecio(String nombre, Double precio) {

        if (Objects.equals(nombre, "")){
            return productoRepo.findAllByPrecio(precio);
        } else if (precio == null) {
            return productoRepo.findAllByNombreContaining(nombre);
        }else {
            return productoRepo.findAllByNombreContainingAndPrecio(nombre, precio);
        }
    }

}
