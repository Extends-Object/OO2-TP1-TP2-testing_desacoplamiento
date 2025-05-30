package pedidos;

public class TarjetaMastercard implements Tarjeta {
    @Override
    public double calcularMontoFinal(double totalBebidas, double totalPlatos) {
        return totalBebidas + (totalPlatos * 0.98);     //desc 2% sobre platos
    }
}