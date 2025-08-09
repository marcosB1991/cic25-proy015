package es.cic.curso25.proy015.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name="tipoVehiculo")
    private String tipoVehiculo;

    @Column (name="matricula")
    private String matricula;

    @Column (name="vehiculoActivo")
    private boolean vehiculoActivo;

    @ManyToOne(optional = true)
    @JoinColumn(name= "plaza_id")
    private Plaza plaza;

    @OneToMany(mappedBy = "vehiculo")
    private List <Multa> multas =new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isVehiculoActivo() {
        return vehiculoActivo;
    }

    public void setVehiculoActivo(boolean vehiculoActivo) {
        this.vehiculoActivo = vehiculoActivo;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", tipoVehiculo=" + tipoVehiculo + ", matricula=" + matricula
                + ", vehiculoActivo=" + vehiculoActivo + ", plaza=" + plaza + ", multas=" + multas + "]";
    }


}
