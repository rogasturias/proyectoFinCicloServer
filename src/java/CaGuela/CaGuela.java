/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaGuela;

import Objetos.Modelos.Cliente;
import Objetos.Modelos.Direccion;
import Objetos.Modelos.Pedido;
import Objetos.Modelos.Posicion;
import Objetos.Modelos.Producto;
import Objetos.Modelos.Servicio;
import Objetos.Modelos.TipoEnvase;
import Objetos.Modelos.Usuario;
import Objetos.ObjetoDAO;
import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author rogas
 */
@WebService(serviceName = "CaGuela")
public class CaGuela {

    @WebMethod(operationName = "enviarUsuario")
    public Objetos.Modelos.Usuario enviarUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Objetos.Modelos.Usuario usuario = (Objetos.Modelos.Usuario) objetoDAO.obtenObjeto(idUsuario, Usuario.class);
        return usuario;
    }

    @WebMethod(operationName = "enviarDireccion")
    public Direccion enviarDireccion(@WebParam(name = "idDireccion") int idDireccion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Objetos.Modelos.Direccion direccion = (Objetos.Modelos.Direccion) objetoDAO.obtenObjeto(idDireccion, Direccion.class);
        return direccion;
    }

    @WebMethod(operationName = "enviarCliente")
    public Cliente enviarCliente(@WebParam(name = "idCliente") int idCliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Cliente cliente = (Cliente) objetoDAO.obtenObjeto(idCliente, Cliente.class);
        return cliente;
    }

    @WebMethod(operationName = "enviarPedido")
    public Pedido enviarPedido(@WebParam(name = "idPedido") int idPedido) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Pedido pedido = (Pedido) objetoDAO.obtenObjeto(idPedido, Pedido.class);
        return pedido;
    }

    @WebMethod(operationName = "enviarPosicion")
    public Posicion enviarPosicion(@WebParam(name = "idPosicion") int idPosicion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Posicion pos = (Posicion) objetoDAO.obtenObjeto(idPosicion, Posicion.class);
        return pos;
    }

    @WebMethod(operationName = "enviarProducto")
    public Producto enviarProducto(@WebParam(name = "idProducto") int idProducto) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Producto producto = (Producto) objetoDAO.obtenObjeto(idProducto, Producto.class);
        return producto;
    }

    @WebMethod(operationName = "enviarServicio")
    public Servicio enviarServicio(@WebParam(name = "idServicio") int idServicio) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Servicio servicio = (Servicio) objetoDAO.obtenObjeto(idServicio, Servicio.class);
        return servicio;
    }

    @WebMethod(operationName = "enviarTipoEnvase")
    public TipoEnvase enviarTipoEnvase(@WebParam(name = "idTipoEnvase") int idTipoEnvase) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        TipoEnvase tipoEnvase = (TipoEnvase) objetoDAO.obtenObjeto(idTipoEnvase, TipoEnvase.class);
        return tipoEnvase;
    }

    @WebMethod(operationName = "login")
    public Cliente login(@WebParam(name = "usuario") String user, @WebParam(name = "clave") String clave) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        Usuario u = new Usuario(user, clave);
        int id = objetoDAO.idExisteObjeto(u);
        Cliente cliente = new Cliente();
        if (id > 0) {
            Usuario usuario = (Usuario) objetoDAO.obtenObjeto(id, Usuario.class);
            List<Object> clientes = objetoDAO.listadoObjetosActivos(Cliente.class);
            for (Object c : clientes) {
                if (((Cliente) c).getUsuario().equals(usuario)) {
                    cliente = (Cliente) c;
                    break;
                }
            }
        }
        return cliente;
    }

    @WebMethod(operationName = "guardarUsuario")
    public int guardarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        if (objetoDAO.guardaNuevoDato(usuario)) {
            return objetoDAO.idExisteObjeto(usuario);
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "actualizaUsuario")
    public boolean actualizaUsuario(@WebParam(name = "usuario") Usuario usuario) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(usuario);

    }

    @WebMethod(operationName = "guardaDireccion")
    public int guardarDireccion(@WebParam(name = "direccion") Direccion direccion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        if (objetoDAO.guardaNuevoDato(direccion)) {
            return objetoDAO.idExisteObjeto(direccion);
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "actualizaDireccion")
    public boolean actualizaDireccion (@WebParam(name = "direccion") Direccion direccion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(direccion);

    }

    @WebMethod(operationName = "guardaCliente")
    public int guardarCliente(@WebParam(name = "cliente") Cliente cliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        if (objetoDAO.guardaNuevoDato(cliente)) {
            return objetoDAO.idExisteObjeto(cliente);
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "actualizaCliente")
    public boolean actualizaCliente(@WebParam(name = "usuario") Cliente cliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(cliente);

    }

    @WebMethod(operationName = "actualizaProducto")
    public boolean actualizaProducto(@WebParam(name = "producto") Producto producto) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(producto);

    }
    @WebMethod(operationName = "guardaProducto")
    public int guardaProducto(@WebParam(name = "producto") Producto producto) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        System.out.println("************.........guardando nuevo el Producto envase "+producto);        
        objetoDAO.guardaNuevoDato(producto);
        return producto.getId();
        
    }
        @WebMethod(operationName = "actualizaTipoEnvase")
    public boolean actualizaTipoEnvase(@WebParam(name = "TipoEnvase") TipoEnvase tipo) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(tipo);

    }
    @WebMethod(operationName = "guardaTipoEnvase")
    public int  guardaTipoEnvase(@WebParam(name = "TipoEnvase") TipoEnvase tipo) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        System.out.println("************.........guardando nuevo el Tipo envase "+tipo);
         objetoDAO.guardaNuevoDato(tipo);
         return tipo.getId();
    }

    @WebMethod(operationName = "guardaPedido")
    public int  guardaPedido (@WebParam(name = "pedido") Pedido pedido) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        System.out.println("************.........guardando nuevo pedido "+pedido.toString());
        objetoDAO.guardaNuevoDato(pedido);
         return pedido.getId();
    }
    
    @WebMethod(operationName = "actualizaPedido")
    public boolean  actualizaPedido (@WebParam(name = "pedido") Pedido pedido) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        System.out.println("************.........actualizando pedido "+pedido.toString());
        return objetoDAO.actualizaDato(pedido);
        
    }
    
    
    @WebMethod(operationName = "guardarPosicion")
    public int guardarPosicion(@WebParam(name = "posicion") Posicion posicion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        if (objetoDAO.guardaNuevoDato(posicion)) {
            return posicion.getId();
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "actualizaPosicion")
    public boolean actualizaPosicion(@WebParam(name = "posicion") Posicion posicion) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.actualizaDato(posicion);

    }
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////  METODOS COMPLEJOS. ///////////////////////////
    ////////////////////////////////////////////////////////////////////////
    
    @WebMethod(operationName = "productosCompradosHabitualmente")
    public Set listadoProductosComprador(@WebParam(name = "cliente") Cliente cliente, @WebParam(name = "activo") boolean activo, @WebParam(name = "recibido") boolean recibido) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoProductosComprador(cliente, activo, recibido);
    }

    @WebMethod(operationName = "pedidosCliente")
    public List listadoPedidosComprador(@WebParam(name = "cliente") Cliente cliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoPedidosCompradorNoRecibidos(cliente);
    }

    @WebMethod(operationName = "pedidosVendedor")
    public List listadoPedidosVendedor(@WebParam(name = "cliente") Cliente cliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoPedidosVendedorNoRecibidos(cliente);
    }

    @WebMethod(operationName = "productosGamaVendedor")
    public List ProductosVendedor(@WebParam(name = "cliente") Cliente cliente) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoProductosVendedor(cliente);
    }

    @WebMethod(operationName = "todosVendedores")
    public List TodosVendedores() {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoTodosvendedores();
    }

    @WebMethod(operationName = "posicionesPendientesVendedor")
    public List posicionesPendienteVendedor(@WebParam(name = "clliente")Cliente cliente ,@WebParam(name = "vendedor")boolean vendedor ) {
        ObjetoDAO objetoDAO = new ObjetoDAO();
        return objetoDAO.listadoPosicionesPendientes(cliente,vendedor);
    }
    
    
}
