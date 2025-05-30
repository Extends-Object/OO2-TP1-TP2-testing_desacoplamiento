package pedidos.persistencia;

import concurso.utilidades.exceptions.DatabaseConnectionException;

import java.time.LocalDate;

public interface Repositorio {
    public void guardarPedido(LocalDate fecha, double monto) throws DatabaseConnectionException;
}
