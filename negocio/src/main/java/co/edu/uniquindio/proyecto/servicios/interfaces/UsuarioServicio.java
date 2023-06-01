package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.modelo.dto.*;

import java.util.List;

public interface UsuarioServicio {

    Integer registarUsuario(UsuarioPostDTO u) throws Exception;

    UsuarioGetDTO actualizarUsuario(Integer cedula, UsuarioPostDTO u) throws Exception;

    void eliminarUsuario(Integer cedula) throws Exception;

    List<UsuarioGetDTO> listarUsuarios();

    List<ProductoGetDTO> listaFavoritos(String email) throws Exception;

    UsuarioGetDTO obtenerUsuario (Integer codigo) throws Exception;

    UsuarioGetDTO obtenerUsuarioEmail (String email) throws Exception;

    TokenDTO iniciarSesion(SesionDTO sesionDTO);

    TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception;

}
