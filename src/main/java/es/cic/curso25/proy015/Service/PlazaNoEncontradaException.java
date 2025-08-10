package es.cic.curso25.proy015.Service;

public class PlazaNoEncontradaException extends RuntimeException {
    public PlazaNoEncontradaException() {
        super("Plaza no encontrada");
    }
}