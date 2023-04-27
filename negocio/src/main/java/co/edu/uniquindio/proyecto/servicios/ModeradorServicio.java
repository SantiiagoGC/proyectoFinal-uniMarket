package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.ProductoModerador;

import java.util.List;

import co.edu.uniquindio.proyecto.entidades.Moderador;


public interface ModeradorServicio {

    Moderador iniciarSesion(String email, String password) throws Exception;


    List<ProductoModerador> consultarEstado (Estado estado) throws Exception ;


    String aceptarProducto(Integer codigo) throws Exception;

    String rechazarProducto(Integer codigo) throws Exception;

}

