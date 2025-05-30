package pedidos;

public class Bebida extends Producto{


    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
