package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.FavoritoRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final ComentarioRepo comentarioRepo;

    private final FavoritoRepo favoritoRepo;

    private final CategoriaRepo categoriaRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, FavoritoRepo favoritoRepo, CategoriaRepo categoriaRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.favoritoRepo = favoritoRepo;
        this.categoriaRepo = categoriaRepo;
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
        Optional<Producto> buscado = productoRepo.findById(producto.getId());
        if ( buscado.isEmpty() ){
            throw new Exception("El producto no existe.");
        }
        productoRepo.save(producto);
    }

    @Override
    public Producto obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigoProducto).orElseThrow( () -> new ProductoNoEncontradoException("El codigo del producto no es válido") );
    }

    @Override
    public String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        return producto.get().getDescripcion();
    }


    @Override
    public List<Producto> listarProducto() throws Exception{
        List<Producto> productos = productoRepo.findAll();
        //productos.forEach(System.out::println);
        if(productos.isEmpty()){
            throw new Exception("No hay productos disponibles");
        }
        return productos;
    }

    @Override
    public Comentario comentarProducto(Comentario comentario) throws Exception {
        if(comentario != null){
            comentarioRepo.save(comentario);
        }

        return comentario;
    }

    @Override
    public Favorito guardarProductoFavoritos(Favorito favorito) throws Exception {
        if (favorito == null){
            throw new Exception("No se encuentra el favorito");
        }

        return favoritoRepo.save(favorito);
    }

    @Override
    public void eliminarProductoFavoritos(Favorito favorito) throws Exception {
        Optional<Favorito> favoritoEncontrado = favoritoRepo.findById(favorito.getId());

        if (favoritoEncontrado.isEmpty()){
            throw new Exception("No se ha encontrado el favorito");
        }else {
            favoritoRepo.delete(favoritoEncontrado.get());
        }
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

    @Override
    public String obtenerEmailVendedor(Integer idProducto) throws Exception{
        List<Producto> productos = productoRepo.findAll();
        for (Producto producto : productos) {
            if (producto.getId().equals(idProducto)) {
                return productoRepo.obtenerEmailVendedor(idProducto);
            }
        }
        throw new Exception("No se encontro el producto.");
    }

    @Override
    public List<Categoria> listarProductosCategoria(String categoria) throws Exception {

        return categoriaRepo.findAllByNombreIgnoreCase(categoria);
    }

    @Override
    public LocalDate obtenerFechaLimite(Integer idProducto) throws Exception{

        Optional<Producto> productos = productoRepo.findById(idProducto);
        if(productos.isPresent()){
            return productoRepo.obtenerFechaLimite(idProducto);
        }else{
            throw new Exception("Producto no encontrado.");
        }
    }

}
