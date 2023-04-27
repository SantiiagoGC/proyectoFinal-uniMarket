package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;

public interface ModeradorServicio {

    Moderador iniciarSesion(String email, String password) throws Exception;

    String aceptarProducto(Integer codigo) throws Exception;

    String rechazarProducto(Integer codigo) throws Exception;

}
