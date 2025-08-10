package es.cic.curso25.proy015.Service;

public class VehiculoNoEncontradoException extends RuntimeException {

     public VehiculoNoEncontradoException() {
        super("Vehículo no encontrado");
    }
}
