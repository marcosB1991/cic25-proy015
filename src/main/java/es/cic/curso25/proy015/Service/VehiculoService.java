package es.cic.curso25.proy015.Service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy015.Model.Plaza;
import es.cic.curso25.proy015.Model.Vehiculo;
import es.cic.curso25.proy015.Repository.PlazaRepository;
import es.cic.curso25.proy015.Repository.VehiculoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehiculoService {

    private static final Logger logger = LoggerFactory.getLogger(VehiculoService.class);

    private VehiculoRepository vehiculoRepository;
    private PlazaRepository plazaRepository;
    private MultaService multaService;

    public VehiculoService(VehiculoRepository vehiculoRepository,
            PlazaRepository plazaRepository,
            MultaService multaService) {
        this.vehiculoRepository = vehiculoRepository;
        this.plazaRepository = plazaRepository;
        this.multaService = multaService;
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehiculo obtenerVehiculo(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException("Vehículo no encontrado"));
    }

    public Vehiculo updateVehiculo(Vehiculo vehiculo) {
        if (!vehiculoRepository.existsById(vehiculo.getId())) {
            throw new VehiculoNotFoundException("Vehículo no encontrado");
        }
        return vehiculoRepository.save(vehiculo);
    }

    public void aparcarVehiculo(Long vehiculoId, Long plazaId) {
        logger.info("Intentando aparcar vehículo {} en plaza {}\", vehiculoId, plazaId");

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(VehiculoNoEncontradoException::new);

        Plaza plaza = plazaRepository.findById(plazaId)
                .orElseThrow(PlazaNoEncontradaException::new);

        if (plaza.isOcupada()) {
            throw new PlazaOcupadaException();
        }

        // Si el vehículo ya tiene otra plaza asignada distinta, multa
        if (vehiculo.getPlaza() != null && !vehiculo.getPlaza().getId().equals(plazaId)) {
            logger.warn("Vehículo {} aparcó fuera de su plaza asignada. Multando...", vehiculoId);
            multaService.crearMulta(vehiculoId, LocalDate.now());
        }

        // Asignar plaza y marcar ocupada
        vehiculo.setPlaza(plaza);
        plaza.setOcupada(true);

        vehiculoRepository.save(vehiculo);
        plazaRepository.save(plaza);

        logger.info("Vehículo {} aparcado en plaza {}", vehiculoId, plazaId);

    }

}
