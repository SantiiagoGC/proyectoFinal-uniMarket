package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;

import java.util.List;

public interface UsuarioServicio {

    Integer registarUsuario(UsuarioPostDTO u) throws Exception;

    UsuarioGetDTO actualizarUsuario(Integer cedula, UsuarioPostDTO u) throws Exception;

    void eliminarUsuario(Integer cedula) throws Exception;

    List<UsuarioGetDTO> listarUsuarios();

    List<Favorito> listaFavoritos(String email) throws Exception;

    UsuarioGetDTO obtenerUsuario (Integer codigo) throws Exception;

    Usuario iniciarSesion(String email, String password) throws Exception;

}
