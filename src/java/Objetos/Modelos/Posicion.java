/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author rogas
 */
@Entity
public class Posicion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    @OneToOne (fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    private Producto producto;
    
    private int cantidad;
    
   
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pedido pedido;
    
    

    public Posicion() {
    }

    public Posicion(Producto producto, int Cantidad) {
        this.producto = producto;
        this.cantidad = Cantidad;
        
       
    }

    public Posicion(Producto producto, int cantidad, Pedido pedido) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.pedido = pedido;
    }
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.cantidad = Cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

   

    @Override
    public String toString() {
        return "Posicion{" + "id=" + id + ", producto=" + producto + ", Cantidad=" + cantidad + ", pedido=" + pedido + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Posicion other = (Posicion) obj;
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return Objects.equals(this.pedido, other.pedido);
    }
    
    
    
    
    
    
    
}
