package es.cic.curso25.proy015.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proy015.Service.MaxPlazasAlcanzadoException;
import es.cic.curso25.proy015.Service.PlazaNoEncontradaException;
import es.cic.curso25.proy015.Service.PlazaOcupadaException;
import es.cic.curso25.proy015.Service.VehiculoNoEncontradoException;

@RestControllerAdvice
public class MiControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiControllerAdvice.class);

    @ExceptionHandler(MaxPlazasAlcanzadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMaxPlazas(MaxPlazasAlcanzadoException ex) {
        LOGGER.warn("{}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(PlazaNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePlazaNoEncontrada(PlazaNoEncontradaException ex) {
        LOGGER.warn("{}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(PlazaOcupadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handlePlazaOcupada(PlazaOcupadaException ex) {
        LOGGER.warn("{}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(VehiculoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleVehiculoNoEncontrado(VehiculoNoEncontradoException ex) {
        LOGGER.warn("{}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }
}