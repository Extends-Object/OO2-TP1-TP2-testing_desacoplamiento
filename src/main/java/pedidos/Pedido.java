package pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int id = 1;

    private List<Producto> listaPlatos;
    private List<Producto> listaBebidas;

    private float subtotalPlatos;
    private float subtotalBebidas;


    public Pedido() {

        this.id ++;

        this.listaPlatos = new ArrayList<>();
        this.listaBebidas = new ArrayList<>();

        this.subtotalPlatos = 0;
        this.subtotalBebidas = 0;
    }


    public void cargarPlato (Producto plato, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            this.listaPlatos.add(plato);
        }
    }

    public void cargarBebida (Producto bebida, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            this.listaBebidas.add(bebida);
        }
    }



    public float calcularSubtotalPlatos(){
        this.subtotalPlatos = 0;
        for(Producto producto : listaPlatos){
            this.subtotalPlatos += producto.getPrecio();
        }
        return this.subtotalPlatos;
    }


    public float calcularSubtotalBebidas(){
        this.subtotalBebidas = 0;
        for(Producto producto : listaBebidas){
            this.subtotalBebidas+= producto.getPrecio();
        }
        return this.subtotalBebidas;
    }
}