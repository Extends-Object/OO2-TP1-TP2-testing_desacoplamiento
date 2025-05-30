import concurso.utilidades.exceptions.DatabaseConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedidos.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosTest {

    Pedido pedido;
    RepositorioPedidosFake repositorio;
    DispositivoElectronico dispositivo;
    LocalDate fechaPedido;
    float montoFinal;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.cargarPlato(new Plato("Tallarines", 3560), 2);
        pedido.cargarBebida(new Bebida("Seven up", (float)1780.5), 1);  //subtotal 8900.50

        repositorio = new RepositorioPedidosFake();
        dispositivo = new DispositivoElectronico(repositorio);

        fechaPedido = LocalDate.now();
    }


    @Test
    public void pagoConVisaTest () {      //SIEMPRE SON VOID Y NO RECIBEN PARAMETROS

        Tarjeta tarjeta = new TarjetaVisa();        //El descuento con Visa da 53.403

        //Total con el descuento da 8847.097
        //modelo.Propina es de 442.35485
        //Total mas la propina da 9289.45185

        //Exercise
        try {
            montoFinal = dispositivo.calcularTotal(pedido, tarjeta, Propina.CINCO_PORCIENTO, fechaPedido);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        //Verify
        assertEquals(9289.439F, montoFinal, "El monto calculado no es correcto.");
        assertFalse(repositorio.getListaPedidos().isEmpty());
    }


    @Test
    public void pagoConMastercardTest () {

        Tarjeta tarjeta = new TarjetaMastercard();        //El descuento con Master da 142.408

        //Total con el descuento da 8758.092
        //modelo.Propina es de 262.74276
        //Total con descuento mas la propina da 9020.835

        //Exercise
        try {
            montoFinal = dispositivo.calcularTotal(pedido, tarjeta, Propina.TRES_PORCIENTO, fechaPedido);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        //Verify
        assertEquals(9020.843F, montoFinal, "El monto calculado no es correcto.");
        assertFalse(repositorio.getListaPedidos().isEmpty());
    }


    @Test
    public void pagoConComarca () {

        Tarjeta tarjeta = new TarjetaComarca();        //El descuento con Comarca da 178.01

        //Total con el descuento da 8722.49
        //modelo.Propina es de 174.4498
        //Total con descuento mas la propina da 8896.9398

        //Exercise
        try {
            montoFinal = dispositivo.calcularTotal(pedido, tarjeta, Propina.DOS_PORCIENTO, fechaPedido);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        //Verify
        assertEquals(8896.94F, montoFinal, "El monto calculado no es correcto");
        assertFalse(repositorio.getListaPedidos().isEmpty());
    }


    @Test
    public void pagoConGenerica () {

        Tarjeta tarjeta = new TarjetaGenerica();        //No tiene descuento

        //modelo.Propina es de 445.025
        //Total con descuento mas la propina da 9345.525

        //Exercise
        try {
            montoFinal = dispositivo.calcularTotal(pedido, tarjeta, Propina.CINCO_PORCIENTO, fechaPedido);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        //Verify
        assertEquals(9345.525F, montoFinal, "El monto final no es correcto");
        assertFalse(repositorio.getListaPedidos().isEmpty());
    }
}
