package es.cic.curso25.proy015.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proy015.Model.Multa;
import es.cic.curso25.proy015.Model.Vehiculo;
import es.cic.curso25.proy015.Repository.MultaRepository;
import es.cic.curso25.proy015.Repository.VehiculoRepository;

@Service
@Transactional
public class MultaService {

    private static final Logger logger = LoggerFactory.getLogger(MultaService.class);

    private MultaRepository multaRepository;
    private VehiculoRepository vehiculoRepository;

    public MultaService(MultaRepository multaRepository, VehiculoRepository vehiculoRepository) {
        this.multaRepository = multaRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public Multa crearMulta(Long vehiculoId, LocalDate fechaEntrada) {
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(VehiculoNoEncontradoException::new);

        Multa multa = new Multa();
        multa.setVehiculo(vehiculo); // vincular multa al coche
        multa.setFechaEntrada(fechaEntrada); // establecer fecha
        multa.setFechaSalida(null); // salida todavia no definida

        return multaRepository.save(multa);
    }

    public Multa cerrarMulta(Long multaId, LocalDate fechaSalida) {
        Multa multa = multaRepository.findById(multaId)
                .orElseThrow(() -> new RuntimeException("Multa no encontrado"));

        multa.setFechaSalida(fechaSalida);

            // Calcular diferencia en días
    long dias = ChronoUnit.DAYS.between(multa.getFechaEntrada(), fechaSalida);
    if (dias <= 0) {
        dias = 1; // mínimo un día
    }

    // Calcular importe
    multa.setImporte(dias * Multa.PRECIO);

        return multaRepository.save(multa);

    }

}
