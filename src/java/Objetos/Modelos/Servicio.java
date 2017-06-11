/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Modelos;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author rogas
 */
@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private int port;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    private boolean resultado;

    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cliente cliente;

    public Servicio() {
    }

    public Servicio(String address, int port, Date fecha, Cliente cliente) {
        this.address = address;
        this.port = port;
        this.fecha = fecha;
        this.cliente = cliente;
    }
    

    public Servicio(InetAddress address, int port, Date fecha) {
        this.address = address.toString();
        this.port = port;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address.toString();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "servicio{ address=" + address + ", port=" + port + "fecha: " + fecha + "resultado"+resultado+ "idCliente"+cliente+ '}';
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
        final Servicio other = (Servicio) obj;
        if (this.port != other.port) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

}
