import concurso.Concurso;
import concurso.Participante;
import concurso.persistencia.Repositorio;
import concurso.utilidades.exceptions.DatabaseConnectionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioConcursoFake implements Repositorio {

    List<Participante> inscriptos = new ArrayList<>();

    @Override
    public void guardarInscripcion(LocalDate fecha, Participante participante, Concurso concurso) throws DatabaseConnectionException {
        this.inscriptos.add(participante);
    }
}
