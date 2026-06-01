package proj26E3;
/**
 * Representa um pedido efetuado por um (FuncionarioBar).
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private int id; //identificador do pedido
	private LocalDate dataHora; //data em que o pedido foi criado
	private double total;// total acumulado do pedido
	private ArrayList <Item> itens;

	public Pedido(int id) {
		this.id = id;
		this.dataHora = LocalDate.now();
		this.itens = new ArrayList<>();
	}

	public double getTotal() {
		return calcularTotal();
	}
	
	public int getId() {
		return id;
	}

    public LocalDate getDataHora() {
		return dataHora;
	}

    public ArrayList<Item> getItens() {
		return itens;
	}

	public void adicionarItem(Produto produto, int quantidade) {
        Item item = new Item(quantidade, produto);
        itens.add(item);
        //item.reduzir();
    }
    
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
    
   public int getQuantidadeProdutos() { // colocar no diagrama
        int total = 0;

        for(Item item : itens) {
            total += item.getQtd();
        }

        return total;
    }
   
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataHora=" + dataHora + ", total=" + total + ", itensP=" + itens + "]";
	}

}
