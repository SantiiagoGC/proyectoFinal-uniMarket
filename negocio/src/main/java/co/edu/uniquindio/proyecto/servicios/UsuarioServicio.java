package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(String cedula) throws Exception;

    List<Usuario> listarUsuario();

    List<Favorito> listaFavoritos(String email) throws Exception;

    Usuario obtenerUsuario (String codigo) throws Exception;

    Usuario iniciarSesion(String email, String password) throws Exception;

    void publicarProducto(Usuario u, Producto p) throws Exception;

}
