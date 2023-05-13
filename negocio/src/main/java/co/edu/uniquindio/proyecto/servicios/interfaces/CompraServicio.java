package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.modelo.dto.CompraPostDTO;

import java.util.List;

public interface CompraServicio {

    List<Compra> listarComprasUsuario (String cedula);

    /*
    Implementado creo
     */
    Compra crearCompra(Integer unidades, Integer codigoProducto, CompraPostDTO compraPostDTO) throws Exception;

    Compra actualizarCompra(Compra c) throws Exception;

    void eliminarCompra(Compra c) throws Exception;

    Compra obtenerCompra(Integer id) throws Exception;

    String obtenerCorreoComprador (Integer id) throws Exception;

    List<String> obtenerCorreosVendedores (Integer id) throws Exception;


}
