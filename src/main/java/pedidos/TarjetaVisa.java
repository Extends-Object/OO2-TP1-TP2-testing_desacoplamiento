package pedidos;

public class TarjetaVisa implements Tarjeta {
    @Override
    public double calcularMontoFinal(double totalBebidas, double totalPlatos) {
        return (totalBebidas * 0.97) + totalPlatos;     //desc 3% sobre bebidas
    }
}
