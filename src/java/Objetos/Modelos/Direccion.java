/*
 *  @XmlID    
@XmlAttribute    private String id;
@XmlAttribute    private String name;       @XmlIDREF
 */
package Objetos.Modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rogas
 */
@Entity
public class Direccion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int id;
     
    private String callePiso;
    private String codigoPostal;
    private String ciudad;
    private String comunidadAutonoma;
    private String pais;
    private boolean activo;
   
 
    public Direccion() {
    }

    public Direccion(String callePiso, String codigoPostal, String ciudad, String comunidadAutonoma, String pais) {
        this.callePiso = callePiso;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.comunidadAutonoma = comunidadAutonoma;
        this.pais = pais;
        this.activo=true;
    }

    public Direccion(String callePiso,  String codigoPostal) {
        this.callePiso=callePiso;
        this.codigoPostal = codigoPostal;
        this.activo=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallePiso() {
        return callePiso;
    }

    public void setCallePiso(String callePiso) {
        this.callePiso = callePiso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

   

    @Override
    public String toString() {
        return "Direccion{" + "id=" + id + ", nombreCalle=" + callePiso + ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + ", comunidadAutonoma=" + comunidadAutonoma + ", pais=" + pais + ", cliente=" +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Direccion other = (Direccion) obj;
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.callePiso, other.callePiso)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.comunidadAutonoma, other.comunidadAutonoma)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        return true;
    }

    

   

    

   
    
    
    
    
    
    
    
    
}
