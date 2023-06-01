package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.modelo.dto.FavoritoPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ProductoPostDTO;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final ComentarioRepo comentarioRepo;

    private final FavoritoRepo favoritoRepo;

    private final CategoriaRepo categoriaRepo;

    private final UsuarioRepo usuarioRepo;

    private final ImagenRepo imagenRepo;

    /*
    Publicar Producto implementado con DTOS
     */
    @Override
    public Integer publicarProducto(ProductoPostDTO producto) throws Exception {
        try{

            Producto pro = new Producto();
            pro.setNombre( producto.getNombre() );
            pro.setDescripcion( producto.getDescripcion() );
            pro.setUnidades( producto.getUnidades() );
            pro.setPrecio( producto.getPrecio() );
            Optional<Usuario> encontrado = usuarioRepo.findById(producto.getCedulaVendedor());
            pro.setVendedor(encontrado.get());
            pro.setFechaCreado( LocalDate.now() );
            pro.setFechaLimite( LocalDate.now().plusDays(60) );
            pro.setActivo(false);
            pro.setCategoria( obtenerCategorias2(producto.getCodigoCategoria()) );

            return productoRepo.save(pro).getId();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /*
    Metodo para guardar imagenes desde Strings
     */
    public List<Imagen> guardarImagenes(List<String> rutas){

        List<Imagen> respuesta = new ArrayList<>();

        for(String ruta: rutas) {
            Imagen imagen = new Imagen();
            imagen.setRuta(ruta);
            respuesta.add( imagenRepo.save(imagen) );
        }

        return respuesta;
    }


    public Categoria obtenerCategorias2(Integer idCategoria){
        Optional<Categoria> encontrado = categoriaRepo.findById(idCategoria);
        return encontrado.orElse(null);
    }


    /*
    Metodo para eliminar producto implementado
     */
    @Override
    public void  eliminarProducto(Integer codigoProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if( producto.isEmpty() ){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigoProducto);
    }

    @Override
    public List<ProductoGetDTO> listarPorEstado(boolean estado) {

        List<Producto> productosPorEstado = productoRepo.findByActivo(estado);
        List<ProductoGetDTO> productoGetDTOList = new ArrayList<>();

        for (Producto producto : productosPorEstado) {
            productoGetDTOList.add(convertir(producto));
        }

        return productoGetDTOList;
    }

    /*
    Metodo para actualizar funcional con DTO
     */
    @Override
    public void actualizarProducto(Integer codigo, ProductoPostDTO p) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(codigo);
        if ( buscado.isEmpty() ){
            throw new Exception("El producto no existe.");
        }

        Producto producto = buscado.get();
        producto.setNombre( p.getNombre() );
        producto.setDescripcion( p.getDescripcion() );
        producto.setPrecio( p.getPrecio() );
        producto.setUnidades( p.getUnidades() );
        producto.setImagenesProducto( guardarImagenes(p.getRutasImagenes()) );
        producto.setCategoria( obtenerCategorias2(p.getCodigoCategoria()) );
        productoRepo.save(producto);
    }

    /*
    Metodo para obtener un producto correcto
     */
    @Override
    public ProductoGetDTO obtenerProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        return convertir(productoRepo.findById(codigoProducto).orElseThrow( () -> new ProductoNoEncontradoException(
                "El codigo del producto no es válido")));
    }

    /*
    Obetener descripcion correcto
     */
    @Override
    public String obtenerDescripcionProducto(Integer codigoProducto) throws ProductoNoEncontradoException {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if(producto.isPresent()){
            return producto.get().getDescripcion();
        }else{
            throw new ProductoNoEncontradoException("Producto no encontrado");
        }
    }

    /*
    Listar productos correcto con DTO
     */
    @Override
    public List<ProductoGetDTO> listarProducto() {
        return convertirLista( productoRepo.findAll() );

    }

    @Override
    public List<ProductoGetDTO> listarProductosPersona(Integer cedula) {

        Optional<Usuario> persona = usuarioRepo.findById(cedula);

        if (persona.isPresent()){
            Usuario usuario = persona.get();
            List<Producto> ventas = usuario.getProductosVenta();
            return convertirLista(ventas);
        }

        return null;
    }

    @Override
    public List<ProductoGetDTO> listProductByTitle(String title) {

        List<Producto> productsByTitle = productoRepo.listProductByTitle(title);

        List<ProductoGetDTO> lista = convertirLista(productsByTitle);

        return lista;
    }

    @Override
    public List<ProductoGetDTO> listarProductosFavoritos(Integer cedula) {

        Optional<Usuario> persona = usuarioRepo.findById(cedula);
        List<ProductoGetDTO> productosFavs = new ArrayList<>();

        if (persona.isPresent()){
            Usuario usuario = persona.get();
            List<Favorito> favs = usuario.getProductosFavoritos();
            for (int i = 0; i<= favs.size(); i++){
                productosFavs.add(convertir(favs.get(i).getProducto()));
            }
            return productosFavs;
        }

        return null;
    }

    /*
    Convertir listas de productos a DTO para presentacion en front
    */
    private List<ProductoGetDTO> convertirLista(List<Producto> lista){
        List<ProductoGetDTO> respuesta = new ArrayList<>();
        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }

    /*
   Convertir de adentro hacia afuera
    */
    private  ProductoGetDTO convertir(Producto producto){
        return new ProductoGetDTO(
                producto.getNombre(),
                producto.getUnidades(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getVendedor().getCedula(),
                rutasImagenes(producto.getImagenesProducto()),
                producto.getCategoria().getCodigo());
    }

    /*
    Tranformas rutas en solo strings para el front
     */
    public List<String> rutasImagenes(List<Imagen> rutas){
        List<String> respues = new ArrayList<>();

        for (Imagen ruta : rutas) {
            String url = ruta.getRuta();
            respues.add(url);
        }
            return respues;
    }

    /*
    Convertir categorias para el front
     */
    public List<Integer> categoriasID(List<Categoria> categorias){
        List<Integer> respues = new ArrayList<>();

        for (Categoria categoria : categorias) {
            int codigoC = categoria.getCodigo();
            respues.add(codigoC);
        }

        return  respues;
    }

    /*
    Comentario implementado con DTOS
     */
    /* Esta en comentario servicio
    @Override
    public Integer comentarProducto(ComentarioPostDTO comentario) throws Exception {
        if(comentario != null){
            Comentario comentarioBack = new Comentario();
            comentarioBack.setMensaje( comentario.getMensaje() );
            comentarioBack.setFechaCreacion( LocalDate.now() );
            Optional<Usuario> encontradoU = usuarioRepo.findById(comentario.getCedulaUsuario());
            encontradoU.ifPresent(comentarioBack::setUsuario);
            Optional<Producto> encontradoP = productoRepo.findById(comentario.getIdProducto());
            encontradoP.ifPresent(comentarioBack::setProducto);
            comentarioRepo.save(comentarioBack);
            return comentarioBack.getId();
        }else {
            throw new Exception("No hay comentario");
        }
    }
    */

    @Override
    public List<ProductoGetDTO> listarProductosPorRangoPrecio(Double minPrice, Double maxPrice) {
        List<Producto> productosPrecios = productoRepo.listarPorPrecio(minPrice, maxPrice);
        List<ProductoGetDTO> productosPreciosDTO = new ArrayList<>();

        for (Producto producto: productosPrecios) {
            productosPreciosDTO.add(convertir(producto));
        }

        return productosPreciosDTO;
    }

    /*
    Guardar producto en favoritos implementado con DTO
     */
    @Override
    public void guardarProductoFavoritos(FavoritoPostDTO favorito) throws Exception {
        if (favorito != null){
            Favorito favoritoBack = new Favorito();
            Optional<Producto> encontradoP = productoRepo.findById(favorito.getCodigoProducto());
            encontradoP.ifPresent(favoritoBack::setProducto);
            Optional<Usuario> encontradoU = usuarioRepo.findById(favorito.getCedulaUsuario());
            encontradoU.ifPresent(favoritoBack::setUsuario);
            favoritoRepo.save(favoritoBack);
        }else {
            throw new Exception("WTF, no hay favorito");
        }

    }

    /*
    Eliminar de favoritos implementado
     */
    @Override
    public void eliminarProductoFavoritos(Integer cedula,Integer codigoProducto) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(cedula);
        Favorito favorito = consultarFavoritoByCodigoProducto(codigoProducto);
        if (usuario.isPresent() && favorito != null){
            Usuario usuarioF = usuario.get();
            usuarioF.getProductosFavoritos().remove(favorito);
            usuarioRepo.save(usuarioF);
        }else{
            throw new Exception("Usuario no encontrado Ó Producto no encontrado");
        }

    }

    private Favorito consultarFavoritoByCodigoProducto(Integer codigoProduto) {
        Optional<Favorito> favorito = favoritoRepo.findByProductoId(codigoProduto);
        return favorito.orElse(null);
    }

    /*
    El tiempo lo dira
     */
    @Override
    public List<ProductoGetDTO> listarPorNombreYOPrecio(String nombre, Double precio) {

        if (Objects.equals(nombre, "")){
            return convertirLista(productoRepo.findAllByPrecio(precio));
        } else if (precio == null) {
            return convertirLista(productoRepo.findAllByNombreContaining(nombre));
        }else {
            return convertirLista(productoRepo.findAllByNombreContainingAndPrecio(nombre, precio));
        }
    }

    /*
    El tiempo lo dira
     */
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

    /*
    Acoplado para front
     */
    @Override
    public List<Integer> listarProductosCategoria(String categoria) {

        return categoriasID( categoriaRepo.findAllByNombreIgnoreCase(categoria) );
    }


    @Override
    public List<ProductoGetDTO> listarProductosPorCategoria(String categoria) {

        return convertirLista(buscarProductosCategoria( categoria ));
    }

    @Override
    public int revisarProducto(int idProduct, boolean estado) throws Exception {

        Optional<Producto> producto = productoRepo.findById(idProduct);

        if (producto.isEmpty()){
            throw new Exception("El producto con el id ingresado no existe");
        }

        Producto productoRevisar = producto.get();

        productoRevisar.setActivo(estado);

        productoRepo.save(productoRevisar);

        //emailInterface.sendEmail(new EmailDTO("Su producto a sido " + productReview.getState(), "Su producto sido " + productReview.getState() + " por un moderador", productReview.getUser().getEmail()));

        return productoRevisar.getId();
    }

    public List<Producto> buscarProductosCategoria(String cateogira){
        List<Producto> productos = productoRepo.findAll();
        List<Producto> productosEncontrados = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.getCategoria().getNombre().equalsIgnoreCase(cateogira)) {
                productosEncontrados.add(producto);
            }
        }
        return  productosEncontrados;
    }

    /*
    El tiempo lo dira
     */
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
