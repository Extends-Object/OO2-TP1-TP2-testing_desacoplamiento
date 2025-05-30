package pedidos;

import concurso.utilidades.exceptions.DatabaseConnectionException;
import concurso.utilidades.fechas.Formateador;
import pedidos.persistencia.Repositorio;
import pedidos.persistencia.RepositorioArchivo;
import pedidos.persistencia.RepositorioBD;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Formateador formateador = new Formateador();
        LocalDate fecha = LocalDate.now();

        Pedido pedido = new Pedido();
        pedido.cargarPlato(new Plato("Tallarines", 3560), 2);
        pedido.cargarBebida(new Bebida("Seven up", (float) 1780.5), 1);

        Tarjeta tarjeta = new TarjetaGenerica();        //sin desc


//---------------------------------------- ARCHIVO ---------------------------------------------------------------------
        String rutaArchivo = "C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\Pedidos.txt";
        Repositorio repositorioArchivo = new RepositorioArchivo(rutaArchivo, formateador);

        DispositivoElectronico dispositivo1 = new DispositivoElectronico(repositorioArchivo);

        try {
            dispositivo1.calcularTotal(pedido, tarjeta, Propina.CINCO_PORCIENTO, fecha);
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }


//------------------------------------- BASE DE DATOS ------------------------------------------------------------------

        Repositorio repositorioBD = new RepositorioBD();

        DispositivoElectronico dispositivo2 = new DispositivoElectronico(repositorioBD);

        try {
            dispositivo2.calcularTotal(pedido, tarjeta, Propina.CINCO_PORCIENTO, fecha);
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }


    }
}
