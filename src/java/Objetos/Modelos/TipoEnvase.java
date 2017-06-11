/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rogas
 */
@Entity
public class TipoEnvase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean envaseMetalico;
    private boolean envaseCristal;
    private boolean envasePlastico;
    private boolean refrigerado;
    private boolean pescado;
    private boolean vegetal;
    private boolean animal;
    

    public TipoEnvase() {
    }

    public TipoEnvase(boolean envaseMetalico, boolean envaseCristal, boolean envasePlastico, boolean refrigerado, boolean pescado, boolean vegetal, boolean animal) {
        this.envaseMetalico = envaseMetalico;
        this.envaseCristal = envaseCristal;
        this.envasePlastico = envasePlastico;
        this.refrigerado = refrigerado;
        this.pescado = pescado;
        this.vegetal = vegetal;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    

    public boolean isEnvaseMetalico() {
        return envaseMetalico;
    }

    public void setEnvaseMetalico(boolean envaseMetalico) {
        this.envaseMetalico = envaseMetalico;
    }

    public boolean isEnvaseCristal() {
        return envaseCristal;
    }

    public void setEnvaseCristal(boolean envaseCristal) {
        this.envaseCristal = envaseCristal;
    }

    public boolean isEnvasePlastico() {
        return envasePlastico;
    }

    public void setEnvasePlastico(boolean envasePlastico) {
        this.envasePlastico = envasePlastico;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public boolean isPescado() {
        return pescado;
    }

    public void setPescado(boolean pescado) {
        this.pescado = pescado;
    }

    public boolean isVegetal() {
        return vegetal;
    }

    public void setVegetal(boolean vegetal) {
        this.vegetal = vegetal;
    }

    public boolean isAnimal() {
        return animal;
    }

    public void setAnimal(boolean animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "TipoEnvase{" + "id=" + id + ", envaseMetalico=" + envaseMetalico + ", envaseCristal=" + envaseCristal + ", envasePlastico=" + envasePlastico + ", refrigerado=" + refrigerado + ", pescado=" + pescado + ", vegetal=" + vegetal + ", animal=" + animal + '}';
    }

    
        
}
