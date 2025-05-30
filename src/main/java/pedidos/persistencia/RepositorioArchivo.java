package pedidos.persistencia;

import concurso.utilidades.fechas.Formateador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class RepositorioArchivo implements Repositorio{
    private File archivo;
    private Formateador formateador;

    public RepositorioArchivo(String rutaArchivo, Formateador formateador) {
        this.archivo = new File(rutaArchivo);
        this.formateador = formateador;
    }

    @Override
    public void guardarPedido(LocalDate fecha, double monto) {

        String fechaFormateada = this.formateador.formatearFecha(fecha);
        String informacionVenta = (fechaFormateada + " || " + String.valueOf(monto));

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(this.archivo, true));
            salida.println(informacionVenta);
            salida.close();
            System.out.println("Se actualizó el archivo correctamente.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar inscripción en archivo", e);
        }
    }
}
