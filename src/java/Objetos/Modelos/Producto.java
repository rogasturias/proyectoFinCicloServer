/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Modelos;

import java.io.Serializable;
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
public class Producto implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    private String nombre;
    private String marca;
    private String descripcion;
    private String composicion;
    private String peso;
    private String volumen;
    private int unidades;
    private float tipoIVA;
    private float precioSinIva;
    private boolean activo;
    
    @OneToOne (fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    private TipoEnvase tipoEnvase;
    
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Cliente vendedor;
   

    public Producto(String nombre, String marca, TipoEnvase tipo, String descripcion, 
            String composicion, String peso, String volumen, int unidades,float tipoIVA, 
            float precioSinIva,Cliente cliente) {
        this.nombre = nombre;
        this.marca = marca;
        this.tipoEnvase = tipo;
        this.composicion = composicion;
        this.peso = peso;
        this.volumen = volumen;
        this.unidades = unidades;
        this.descripcion=descripcion;
        this.tipoIVA=tipoIVA;
        this.precioSinIva=precioSinIva;
        this.vendedor=cliente;
    }
    public Producto(){
        this.activo=true;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Cliente getVendedor() {
        return vendedor;
    }

    public void setVendedor(Cliente vendedor) {
        this.vendedor = vendedor;
    }

    
    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(TipoEnvase tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTipoIVA() {
        return tipoIVA;
    }

    public void setTipoIVA(float tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public float getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(float precioSinIva) {
        this.precioSinIva = precioSinIva;
    }
        

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoEnvase getTipo() {
        return tipoEnvase;
    }

    public void setTipo(TipoEnvase tipo) {
        this.tipoEnvase = tipo;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

   
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", descripcion=" + descripcion + ", tipoIVA=" + tipoIVA + ", precioSinIva=" + precioSinIva + ", vendedor=" + vendedor + '}';
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
        final Producto other = (Producto) obj;
        return this.id == other.id;
    }

   
  

   
    
    
    
    
}
