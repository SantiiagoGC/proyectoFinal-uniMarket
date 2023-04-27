package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    List<Compra> listarComprasUsuario (String cedula);

    Compra crearCompra(Compra c) throws Exception;

    Compra actualizarCompra(Compra c) throws Exception;

    void eliminarCompra(Compra c) throws Exception;

    Compra obtenerCompra(Integer id) throws Exception;

    String obtenerCorreoComprador (Integer id) throws Exception;

    List<String> obtenerCorreosVendedores (Integer id) throws Exception;


}
