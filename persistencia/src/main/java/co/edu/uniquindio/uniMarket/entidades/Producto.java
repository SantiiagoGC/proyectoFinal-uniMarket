package co.edu.uniquindio.uniMarket.entidades;

import java.time.LocalDate;
import java.util.List;

public class Producto {
    private boolean estado;
    private String rutaImagen;
    private String nombre;
    private String id;
    private  String descripcion;
    private double precio;
    private boolean disponibilidad;
    private LocalDate fechaLimite;
    private Categoria categoria;
    private List<Comentario> comentarios;
    private List<Double> notas;
    private int cantidad;
}
