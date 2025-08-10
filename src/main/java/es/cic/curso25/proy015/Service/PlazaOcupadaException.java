package es.cic.curso25.proy015.Service;

public class PlazaOcupadaException extends RuntimeException {
    public PlazaOcupadaException() {
        super("La plaza ya está ocupada");
    }
}
