package pedidos.persistencia;

import concurso.persistencia.ConnectionManager;
import concurso.utilidades.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RepositorioBD implements Repositorio{


    @Override
    public void guardarPedido(LocalDate fecha, double monto) throws DatabaseConnectionException {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO ventas (fecha, monto) " + "VALUES (?, ?)");

            statement.setDate(1, Date.valueOf(fecha));
            statement.setFloat(2, (float) monto);

            int cantidad = statement.executeUpdate();
            System.out.println(cantidad);

            if (cantidad == 0) {
                System.out.println("Error al insertar el registro.");
            }

        } catch (SQLException e) {
            System.out.println("Error al procesar la sentencia.");
            throw new DatabaseConnectionException("Error en la conexión: " + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }
}
