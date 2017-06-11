package ConexionTCP;

import Objetos.ObjetoDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import Objetos.Modelos.*;

public class MainServidor {

    public static ArrayList<Servicio> servicios = new ArrayList<>();
    public static int numeroServicios = 0;
    public static int numeroMaximo = 100;
    public static Servicio servicio;
    public static Objetos.ObjetoDAO objetoDao;
    public static Socket skCliente;

    public static void main(String[] arg) throws InterruptedException {

        System.out.println("Este Servidor estara atendiendo indefinidamente cuantos hilos"
                + "\n llegen de forma simultanea y concurrente, registrando cada conexion los datos"
                + "\n del cliente incluido su numero address y port, e imprimiendo al final todos los registros.");

        objetoDao = new ObjetoDAO();
        int puertoServer = 2100;

        boolean apagar = false;
        Random r = new Random();
        ServerSocket skServidor;
        try {
            // Inicio el servidor en el puerto
            skServidor = new ServerSocket(puertoServer);
            System.out.println("Escucho el puerto " + puertoServer);
            while (!apagar) {
                numeroServicios++;
                if (numeroServicios >= numeroMaximo) {
                    apagar = true;
                }

                // Se conecta un cliente
                skCliente = skServidor.accept();

                // creo un serviio
                servicio = new Servicio(skCliente.getInetAddress(), skCliente.getPort(), new Date());
                

                // Atiendo al cliente mediante un thread
                ServidorInterno hilo = new ServidorInterno(skCliente,servicios.size());
                servicios.add(servicio);
                hilo.join();
                hilo.start();
                System.out.println("Cliente conectado y derivado ");

                if (apagar) {
                    skServidor.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("FINALIZA EL SERVER CON  SERVICIOS: " + numeroServicios);
        System.out.println(servicios.toString());
        System.exit(0);

    }

    public static synchronized void addServicio(Servicio servicio) {
        servicios.add(servicio);
        objetoDao.guardaNuevoDato(servicio);

    }

    public static class ServidorInterno extends Thread {

        Socket skClientehilo;
        int numerohilo = servicios.size();
        int idCliente;
        int indexServicio;
        
        public ServidorInterno(Socket sCliente, int idServicio) {
            skClientehilo = sCliente;
            indexServicio=idServicio;
            
        }

        public void run() {
            try {
                // Creo los flujos de entrada y salida

                DataInputStream entrada = new DataInputStream(skClientehilo.getInputStream());
                DataOutputStream salida = new DataOutputStream(skClientehilo.getOutputStream());
                // ATENDER PETICIÓN DEL CLIENTE
                System.out.println(skClientehilo.isConnected() + " conectado");

                salida.writeUTF("Bienvenido");

                idCliente = entrada.readInt();
                String email = entrada.readUTF();
                String asunto = entrada.readUTF();
                String mensaje = entrada.readUTF();

                Cliente cliente = (Cliente) objetoDao.obtenObjeto(idCliente, Cliente.class);
                System.out.println(" el cliente para enviar mail es "+idCliente+" es "+cliente.toString());
                servicios.get(indexServicio).setCliente(cliente);
                

                mensaje += "\n Gracias es ud. el cliente numero: " + numeroServicios + "\n a fecha de: " + new Date() + "\n Servicios La Parra SL le agradece su compra";

                // enviamos el mail.
                boolean i = ConexionTCP.EnvioMail.EnvioMail.sendMail(email, asunto, mensaje);
                
                
                // enviamos dato de confirmacion
                salida.writeBoolean(i);
                System.out.println("***************************************************************El resultado de envio de mail  " + numerohilo + " ha sido " + i);

                // guardo el servicio
                if (i) {
                    servicios.get(indexServicio).setResultado(i);
                    objetoDao.guardaNuevoDato(servicios.get(indexServicio));
                                    }
                skClientehilo.close();
                
                System.out.println("ha finalizado la subrutina main de envio de mail"+servicios.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());

            }

        }

    }
}
