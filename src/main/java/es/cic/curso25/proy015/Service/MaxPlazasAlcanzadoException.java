package es.cic.curso25.proy015.Service;

public class MaxPlazasAlcanzadoException extends RuntimeException {
    public MaxPlazasAlcanzadoException() {
        super("No se pueden crear más de 150 plazas");
    }
}