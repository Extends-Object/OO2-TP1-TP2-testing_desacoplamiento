package concurso.persistencia;

import concurso.Concurso;
import concurso.Participante;
import concurso.utilidades.exceptions.DatabaseConnectionException;

import java.time.LocalDate;

public interface Repositorio {
    public void guardarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) throws DatabaseConnectionException;
}
