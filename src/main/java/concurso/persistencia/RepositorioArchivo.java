package concurso.persistencia;

import concurso.Concurso;
import concurso.Participante;
import concurso.utilidades.fechas.Formateador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class RepositorioArchivo implements Repositorio {
    private File archivo;
    private Formateador formateador;

    public RepositorioArchivo(String rutaArchivo, Formateador formateador) {
        this.archivo = new File(rutaArchivo);
        this.formateador = formateador;
    }

    @Override
    public void guardarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) {

        String fechaFormateada = this.formateador.formatearFecha(fecha);
        String informacionInscripto = (fechaFormateada + ", " + participante.getId() + ", " + concurso.getId());

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(this.archivo, true));
            salida.println(informacionInscripto);
            salida.close();
            System.out.println("Se actualizó en el archivo correctamente.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar inscripción en archivo", e);
        }
    }
}
