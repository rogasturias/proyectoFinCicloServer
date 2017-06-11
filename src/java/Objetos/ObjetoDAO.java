/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Objetos.Modelos.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rogas
 */

public class ObjetoDAO {

    public Session sesion;
    public Transaction tx;

    public ObjetoDAO() {
        iniciaOperacion();
        terminaOperacion();
    }

////////////////////////////////////////////////////////////////////////////////////////
////////////////////////    METODOS DE OBJETOS GENERALES   ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////   
    
   
    public boolean guardaNuevoDato(Object o) throws HibernateException {
        boolean resultado = false;
                
        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        // para determinar el tipo de objeto que es
        boolean pertenece = true;
        if (o instanceof Cliente) {
            Cliente p = (Cliente) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Direccion) {
            Direccion p = (Direccion) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Pedido) {
            Pedido p = (Pedido) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Posicion) {
            Posicion p = (Posicion) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Producto) {
            Producto p = (Producto) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof TipoEnvase) {
            TipoEnvase p = (TipoEnvase) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Usuario) {
            Usuario p = (Usuario) o;
            if(p.getId()!= 0)pertenece=false;
        } else if (o instanceof Servicio) {
            Servicio p = (Servicio) o;
            if(p.getId()!= 0)pertenece=false;
        } else {
            pertenece = false;
        }
        if (pertenece) {
            
            try {

                sesion.persist(o);
                tx.commit();
                 System.out.println("*************************Guardando  el objeto" + "\n " + o.toString());

                resultado = true;

            } catch (HibernateException he) {
                manejaExcepcion(he);
                throw he;
            }
        } else {
            // sino pertenece a nuestra base de datos lanzamos excepcion.
            System.out.println("Este tipo de objeto no esta referenciado en BBDD " + o.toString());
            //throw new HibernateException("Ocurrió un error en la capa de acceso a datos. El tipo de objeto no esta referenciado en tabla");
        }
        if (sesion.isConnected()) {
            sesion.close();
        }
        // si no se ha podido guardar, por que ya existia devolvemos false
        if(!pertenece)System.out.println("***********************************no es un nuevo dato");
        return resultado;
    }

    public boolean actualizaDato(Object o) throws HibernateException {
            if (!sesion.isOpen()) {
            iniciaOperacion();
        }     
         // para determinar el tipo de objeto que es
        boolean pertenece = false;
        if (o instanceof Cliente) {
            Cliente p = (Cliente) o;
            if(p.getId()!= 0)pertenece=true;
           
        } else if (o instanceof Direccion) {
            Direccion p = (Direccion) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof Pedido) {
            Pedido p = (Pedido) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof Posicion) {
            Posicion p = (Posicion) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof Producto) {
            Producto p = (Producto) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof TipoEnvase) {
            TipoEnvase p = (TipoEnvase) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof Usuario) {
            Usuario p = (Usuario) o;
            if(p.getId()!= 0)pertenece=true;
        } else if (o instanceof Servicio) {
            Servicio p = (Servicio) o;
            if(p.getId()!= 0)pertenece=true;
        } 
        
        
               
        // si existe el objeto en la bbdd lo actualizamos.
        if (pertenece) {

            try {
                sesion.update(o);
                tx.commit();
                System.out.println("*************************Actualizando  el objeto" + "\n " + o.toString());

            } catch (HibernateException he) {
                manejaExcepcion(he);
                throw new HibernateException(" no se ha podido actualizar el dato");
            }
                    if (sesion.isConnected()) {
                   sesion.close();
                    }
            return true;
        } // sino existia el objeto devolvemos false
        else {
            return false;
        }
    }

    public Class claseObjeto(Object o) {
        return o.getClass();
    }

    public Object obtenObjeto(int idObjeto, Class clase) throws HibernateException {
        Object object = null;
        if (!sesion.isOpen()) {
            iniciaOperacion();
        }
        try {
            object = (Object) sesion.get(clase, idObjeto);            
        } finally {
            sesion.close();
        }
        return object;
    }

    public List<Object> listadoObjetosActivos(Class clase) {

        boolean existe = true;
        List<Object> listaResultados = null;

        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        if (clase == Direccion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Direccion d ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (!((Direccion) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Cliente.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Cliente p ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (!((Cliente) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Pedido.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Pedido b ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (!((Pedido) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Producto.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Producto r ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (!((Producto) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Posicion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Posicion p ");
            listaResultados = query.list();

        } else if (clase == Servicio.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Servicio p ");
            listaResultados = query.list();

        } else if (clase == TipoEnvase.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM TipoEnvase p ");
            listaResultados = query.list();

        } else if (clase == Usuario.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Usuario p ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (!((Usuario) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } // si no es unn tipo de objeto de la bbdd lanzamos excepcion.
        else {
            System.out.println("no existen objetos de este tipo " + clase.toString());
            throw new HibernateException("Ocurrió un error en la capa de acceso a datos. El tipo de objeto no esta referenciado en tabla");

        }
        if (sesion.isConnected()) {
            sesion.close();
        }
        return listaResultados;

    }

    public List<Object> listadoObjetosNoActivos(Class clase) {

        boolean existe = true;
        List<Object> listaResultados = null;

        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        if (clase == Direccion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Direccion d ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (((Direccion) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Cliente.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Cliente p ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (((Cliente) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Pedido.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Pedido b ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (((Pedido) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Producto.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Producto r ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (((Producto) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } else if (clase == Posicion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Posicion p ");
            listaResultados = query.list();

        } else if (clase == Servicio.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Servicio p ");
            listaResultados = query.list();

        } else if (clase == TipoEnvase.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM TipoEnvase p ");
            listaResultados = query.list();

        } else if (clase == Usuario.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Usuario p ");
            listaResultados = query.list();
            for (Object d : listaResultados) {
                if (((Usuario) d).isActivo()) {
                    listaResultados.remove(d);
                }
            }

        } // si no es unn tipo de objeto de la bbdd lanzamos excepcion.
        else {
            System.out.println("no existen objetos de este tipo " + clase.toString());
            throw new HibernateException("Ocurrió un error en la capa de acceso a datos. El tipo de objeto no esta referenciado en tabla");

        }
        if (sesion.isConnected()) {
            sesion.close();
        }
        return listaResultados;

    }

    public List<Object> listadoObjetosTodos(Class clase) {

        boolean existe = true;
        List<Object> listaResultados = null;

        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        if (clase == Direccion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Direccion d ");
            listaResultados = query.list();

        } else if (clase == Cliente.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Cliente p ");
            listaResultados = query.list();

        } else if (clase == Pedido.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Pedido b ");
            listaResultados = query.list();

        } else if (clase == Producto.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Producto r ");
            listaResultados = query.list();

        } else if (clase == Posicion.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Posicion p ");
            listaResultados = query.list();

        } else if (clase == Servicio.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Servicio p ");
            listaResultados = query.list();

        } else if (clase == TipoEnvase.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM TipoEnvase p ");
            listaResultados = query.list();

        } else if (clase == Usuario.class) {

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Usuario p ");
            listaResultados = query.list();

        } // si no es unn tipo de objeto de la bbdd lanzamos excepcion.
        else {
            System.out.println("no existen objetos de este tipo " + clase.toString());
            throw new HibernateException("Ocurrió un error en la capa de acceso a datos. El tipo de objeto no esta referenciado en tabla");

        }
        if (sesion.isConnected()) {
            sesion.close();
        }
        return listaResultados;

    }

    public boolean eliminaDato(Object o) throws HibernateException {
        boolean resultado = false;

        // pendiente de hacer metodo existe
        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        if (o instanceof Cliente) {
            Cliente p = (Cliente) o;
            p.setActivo(false);
            p.getDireccion().setActivo(false);
            p.getUsuario().setActivo(false);

            // poner inactivo todos los pedidos pendientes 
        } else if (o instanceof Direccion) {
            Direccion p = (Direccion) o;
            ((Direccion) o).setActivo(false);
            resultado = true;
        } else if (o instanceof Pedido) {
            Pedido p = (Pedido) o;
            p.setActivo(false);
            resultado = true;
        } else if (o instanceof Producto) {
            Producto p = (Producto) o;
            p.setActivo(false);
            resultado = true;
        } else if (o instanceof Usuario) {
            Usuario p = (Usuario) o;
            p.setActivo(false);
            resultado = true;
        } else {

            try {

                sesion.delete(o);
                tx.commit();
                resultado = true;

            } catch (HibernateException he) {
                manejaExcepcion(he);
                throw he;
            } finally {
                if (sesion.isConnected()) {
                    sesion.close();
                }
            }
        }
        return resultado;
    }

    public int idExisteObjeto(Object o) {

        int i = -1;
        if (!sesion.isOpen()) {
            iniciaOperacion();
        }

        if (o instanceof Direccion) {
            o = (Direccion) o;
            
            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Direccion d ");
            List<Direccion> listaResultados = query.list();
            for (Direccion a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }

        } else if (o instanceof Cliente) {
            o = (Cliente) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Cliente p ");
            List<Cliente> listaResultados = query.list();
            for (Cliente a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }

        } else if (o instanceof Pedido) {
            o = (Pedido) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Pedido b ");
            List<Pedido> listaResultados = query.list();
            System.out.println("verificando entre los pedidos");
            for (Pedido a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();

                    break;
                }

            }
        } else if (o instanceof Producto) {
            o = (Producto) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Producto r ");
            List<Producto> listaResultados = query.list();
            for (Producto a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }

        } else if (o instanceof Posicion) {
            o = (Posicion) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Posicion p ");
            List<Posicion> listaResultados = query.list();
            for (Posicion a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }
        } else if (o instanceof Servicio) {
            o = (Servicio) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Servicio p ");
            List<Servicio> listaResultados = query.list();
            for (Servicio a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }

        } else if (o instanceof TipoEnvase) {
            o = (TipoEnvase) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM TipoEnvase p ");
            List<TipoEnvase> listaResultados = query.list();
            for (TipoEnvase a : listaResultados) {
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }
        } else if (o instanceof Usuario) {
            o = (Usuario) o;

            // HACEMOS REFERENCIA A LA CLASE NO A LA TABLA.
            Query query = sesion.createQuery("FROM Usuario p ");
            List<Usuario> listaResultados = query.list();
            for (Usuario a : listaResultados) {
                System.out.println(a.toString()+" / "+o.toString());
                if (a.equals(o)) {
                    i = a.getId();
                    break;
                }
            }
        } // si no es unn tipoo de objeto de la bbdd lanzamos excepcion.
        else {
            System.out.println("no existen objetos de este tipo " + o.toString());
            throw new HibernateException("Ocurrió un error en la capa de acceso a datos. El tipo de objeto no esta referenciado en tabla");

        }
        if (sesion.isConnected()) {
            sesion.close();
        }
        return i;

    }

    public boolean existeObjeto(Object o) {

        return idExisteObjeto(o) > 0;

    }
    
    
    

////////////////////////////////////////////////////////////////////////////////////////
////////////////////////    METODOS DE OBJETOS ESPECIFICOS  ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////  
 
    
    
    
    
    public List<Pedido> listadoPedidosComprador(Cliente cliente)    {
        
    if (!sesion.isOpen()) {
            iniciaOperacion();
        }
    
    
    
    String hsql = "FROM Pedido where activo = 1 and comprador_id="+cliente.getId();
    Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();    

        
    if (sesion.isConnected()) {
       sesion.close();
       }
         
    return listaResultados;
    }
    
    public List<Pedido> listadoPedidosCompradorNoRecibidos(Cliente cliente)    {
        
    if (!sesion.isOpen()) {
            iniciaOperacion();
        }
    String hsql = "FROM Pedido where activo = 1 and recibido = 0 and comprador_id="+cliente.getId();
    Query query = sesion.createQuery(hsql);
        List listaResultados = query.list(); 
        
        
    if (sesion.isConnected()) {
         sesion.close();
                }
         
    return listaResultados;
    }
    public List<Pedido> listadoPedidosVendedor(Cliente vendedor)    {
        
    if (!sesion.isOpen()) {
            iniciaOperacion();
        }
    String hsql = "FROM Pedido where activo = 1 and vendedor_id="+vendedor.getId();
    Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
            
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return listaResultados;
   
    }
    public List<Pedido> listadoPedidosVendedorNoRecibidos(Cliente vendedor){
        
    if (!sesion.isOpen()) {
            iniciaOperacion();
        }
    String hsql = "FROM Pedido where activo = 1 and recibido=0 and vendedor_id="+vendedor.getId();
    Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
            
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return listaResultados;
   
    }   
    public Set<Producto> listadoProductosComprador (Cliente comprador, boolean activo, boolean recibido){
       if (!sesion.isOpen()) {
            iniciaOperacion();
        }
       String hsql = "FROM  Posicion pos where  pedido_id in (select pe.id FROM Pedido pe where activo = "+activo+" and recibido = "+recibido+" and comprador_id="+comprador.getId()+")";
        Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
        Set <Producto> productos = new HashSet<>();
            for (Object pos : listaResultados) {
            Producto p = ((Posicion)pos).getProducto();
            productos.add(p);
            }
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return productos;  
     
    }
    public Set<Producto> listadoProductosCompradorToVendedor (Cliente comprador,Cliente vendedor, boolean activo, boolean recibido){
       if (!sesion.isOpen()) {
            iniciaOperacion();
        }
       String hsql = "FROM  Posicion pos where  pedido_id in (select pe.id FROM Pedido pe where activo = "+activo+" and recibido = "+recibido+" and comprador_id="+comprador.getId()+" and vendedor_id= "+vendedor.getId()+")";
        Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
        Set <Producto> productos = new HashSet<>();
            for (Object pos : listaResultados) {
            Producto p = ((Posicion)pos).getProducto();
            productos.add(p);
            }
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return productos;  
     
    }
     
    public List<Producto> listadoProductosVendedor (Cliente vendedor){
       if (!sesion.isOpen()) {
            iniciaOperacion();
        }
       String hsql = "FROM  Producto pos where  vendedor_id = "+vendedor.getId();
        Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
                   
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return listaResultados;  
     
    }
    
    public List<Cliente> listadoTodosvendedores(){
        if (!sesion.isOpen()) {
            iniciaOperacion();
        }
       String hsql = "FROM  Cliente cli where  vendedor= 1";
        Query query = sesion.createQuery(hsql);
        List listaResultados = query.list();
        
        // quito de todos los usuarios,  la clave del listado
            for (Object p : listaResultados) {
            ((Cliente)p).getUsuario().setClave(null);
        }
       
            if (sesion.isConnected()) {
                    sesion.close();
                }
         return listaResultados;  
     
    }
    
     public List<Posicion> listadoPosicionesPendientes (Cliente cliente, boolean vendedor){
         //obtengo array de todos los pedidos pendientes del vendedor y su id
         List <Pedido> pedidosPendientes;
         if (vendedor)  pedidosPendientes = listadoPedidosVendedorNoRecibidos(cliente);
         else pedidosPendientes = listadoPedidosCompradorNoRecibidos(cliente);
         
         Integer[]  idPedidosPendientes = new Integer [pedidosPendientes.size()];                
         int i=0;
         for (Pedido pe : pedidosPendientes) {
             idPedidosPendientes[i]=pe.getId();
             i++;
         }
         
         
         if (!sesion.isOpen()) {
            iniciaOperacion();
        }
         
         //creo una lista para acumular los resultados
          List<Posicion> listaResultados = new ArrayList<>();
          Query query;
          //ejecuto un bucle para ir cargando los pedidos
         for (Integer id : idPedidosPendientes) {
         String hsql = "FROM  Posicion pos where  pedido_id ="+id;
         query = sesion.createQuery(hsql);
         listaResultados.addAll(query.list());  
         }
                         
         if (sesion.isConnected()) {
                    sesion.close();
                }
            //devuelvo las posiciones de los pedidos del vendedor que están pendientes.
         return listaResultados;  
     
    }
    
    
  
    ////////////////////////////////////////////////////
    //////////        METODOS PRIVADOS  ////////////////
    ///////// iniciar, finalizar Except  ///////////////   
    ////////////////////////////////////////////////////   
    public void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public void terminaOperacion() {
        tx.commit();
        //sesion.getTransaction().commit();
        sesion.close();
    }

}
