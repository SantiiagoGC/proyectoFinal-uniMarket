package co.edu.uniquindio.proyecto.excepciones;

import java.util.NoSuchElementException;

public class CompraNoEncontradaException extends NoSuchElementException
{
    public CompraNoEncontradaException(String error){
        super(error);
    }
}
