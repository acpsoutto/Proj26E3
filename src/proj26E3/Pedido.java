package proj26E3;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private int id;
	private LocalDate dataHora;
	private double total;
	private ArrayList <ItemPedido> itensP;
	
	
	public Pedido(int id) {
		this.id = id;
		this.dataHora = LocalDate.now();
		this.total = 0;
		this.itensP = new ArrayList<>();
	}


	public double getTotal() {
		return total;
	}
	
	public int getId() {
		return id;
	}

    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itensP.add(item);
        item.reduzir();
    }
    

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itensP) {
            total += item.calcularSubtotal();
        }
        return total;
    }

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataHora=" + dataHora + ", total=" + total + ", itensP=" + itensP + "]";
	}



	
	
	
}
