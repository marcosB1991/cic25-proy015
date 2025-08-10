package es.cic.curso25.proy015.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy015.Model.Plaza;
import es.cic.curso25.proy015.Model.Vehiculo;
import es.cic.curso25.proy015.Repository.PlazaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlazaService {

    private static final Logger logger = LoggerFactory.getLogger(PlazaService.class);

     private PlazaRepository plazaRepository;

     public PlazaService(PlazaRepository plazaRepository){
        this.plazaRepository=plazaRepository;
     }


     //Supuestamente tendrian que estar inicializadas al arrancar la aplicacion
    public Plaza crearPlaza(Plaza plaza) {
        long totalPlazas = plazaRepository.count();
        long totalVehiculos = plazaRepository.count();
        if (totalPlazas >= 150) {
            throw new MaxPlazasAlcanzadoException();
        }
        return plazaRepository.save(plaza);
    }

    @Transactional(readOnly = true)
    public List<Plaza> listarPlazas(){
        return plazaRepository.findAll();

    }

    @Transactional(readOnly = true)
    public Plaza obtenerPlaza(long id){
        return plazaRepository.findById(id)
            .orElseThrow(PlazaNoEncontradaException::new);
    }

    public Plaza actualizarPlaza (Plaza plaza){
        if (!plaza.isOcupada()){
             throw new PlazaOcupadaException();
        }

        return plazaRepository.save(plaza);
    }



}
