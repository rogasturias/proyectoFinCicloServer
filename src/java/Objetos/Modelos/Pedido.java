/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Modelos;

import java.io.Serializable;
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
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cliente comprador;
    
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cliente vendedor;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
   
    
    private float total;
    private float neto;
    private float iva;
    
    private boolean activo;
    private boolean aceptado;
    private boolean enviado;
    private boolean recibido;
   
   

    public Pedido() {
        this.activo=true;
        this.fecha= new Date();
        
    }

    public Pedido(Cliente comprador, Cliente vendedor, Date fecha) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.activo=true;
        
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Cliente getVendedor() {
        return vendedor;
    }

    public void setVendedor(Cliente vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getNeto() {
        return neto;
    }

    public void setNeto(float neto) {
        this.neto = neto;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }
    
    
   

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id +"recibido ="+recibido+ ", comprador=" + comprador + ", vendedor=" + vendedor + ", fecha=" + fecha + ", total=" + total + ", neto=" + neto + ", iva=" + iva + '}';
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.comprador, other.comprador)) {
            return false;
        }
        if (!Objects.equals(this.vendedor, other.vendedor)) {
            return false;
        }
        if ((this.fecha.getTime()-other.fecha.getTime()) == 0) {
            return false;
        }
        return true;
    }

    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
