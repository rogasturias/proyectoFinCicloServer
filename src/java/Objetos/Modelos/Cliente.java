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
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String apellidos;
    private String IdentificacionFiscal;
    private String descripcion;
    private boolean vendedor;
    private boolean activo;
    
    
    @OneToOne (fetch=FetchType.EAGER, cascade = CascadeType.MERGE )
    private Direccion direccion;
    
    @OneToOne (fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Usuario usuario;
    
   
    
    

    public Cliente() {
       
    }

    public Cliente(String nombre, String apellidos, String IdentificacionFiscal,  Usuario usuario, boolean vendedor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.IdentificacionFiscal = IdentificacionFiscal;
        
        this.usuario = usuario;
        this.vendedor=vendedor;
        this.activo=true;
    }
    
    public Cliente(String nombre, String apellidos, String IdentificacionFiscal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.IdentificacionFiscal = IdentificacionFiscal;
        this.activo=true;
        
    }

    public boolean getTipo() {
        return vendedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setTipo(boolean tipo) {
        this.vendedor = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdentificacionFiscal() {
        return IdentificacionFiscal;
    }

    public void setIdentificacionFiscal(String IdentificacionFiscal) {
        this.IdentificacionFiscal = IdentificacionFiscal;
    }

    public boolean isVendedor() {
        return vendedor;
    }

    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", IdentificacionFiscal=" + IdentificacionFiscal + ", tipo=" + vendedor +  ", usuario=" + usuario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.IdentificacionFiscal, other.IdentificacionFiscal);
    }

   

    

   
    
    
    
    
    
}
