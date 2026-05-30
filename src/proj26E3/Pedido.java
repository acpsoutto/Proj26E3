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
	private ArrayList <ItemPedido> itensP;//lista de item que compoem o pedido
	
	
	/**
	 * CONSTRUTOR
	 * @param id - identificador do pedido
	 */
	public Pedido(int id) {
		this.id = id;
		this.dataHora = LocalDate.now();
		this.total = 0;
		this.itensP = new ArrayList<>();
	}


	/**GET  devolve o valor do produto
	 * @return total
	 */
	public double getTotal() {
		return calcularTotal();
	}
	
	/**
	 * GET devolve o identificador (ID) do pedido
	 * @return id
	 */
	public int getId() {
		return id;
	}

	
    public LocalDate getDataHora() {
		return dataHora;
	}


	/**
     * Adiciona um item ao pedido e reduz imediatamente o stock do produto.
     * @param produto a incluir
     * @param quantidade de unidades
     */
    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itensP.add(item);
        item.reduzir();
    }
    

    /**
     *Calcula e devolve o total do pedido, somando os subtotais de todos os itens.
     * @return total
     */
    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itensP) {
            total += item.calcularSubtotal();
        }
        return total;
    }
    
    public int getQuantidadeProdutos() {
        int total = 0;

        for(ItemPedido item : itensP) {
            total += item.getQuantidade();
        }

        return total;
    }
   

	/**
	 *toString - devolve as informações sobre o pedido
	 */
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataHora=" + dataHora + ", total=" + total + ", itensP=" + itensP + "]";
	}

}
