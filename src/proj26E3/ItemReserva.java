package proj26E3;
/**
 * Class ItemReserva - Representa um item individual dentro de uma reserva.
 */
import java.time.YearMonth;
import java.util.ArrayList;

public class ItemReserva {

    private int quantidadeT;
    private double precoUnitario;
    private Produto produto;
    private ArrayList<Integer> quantidadesL;
    private ArrayList<YearMonth> validadesL;
   
    /**
     * CONSTRUTOR
     * @param produto - o produto que está a ser reservado
     * @param quantidade - quantas unidades o cliente quer reservar
     */
    public ItemReserva(Produto produto, int quantidadeT) {
        this.produto = produto;
        this.quantidadeT = quantidadeT;
        this.precoUnitario = produto.getPreco(); // captura o preço no momento da reserva
        quantidadesL = new ArrayList<>();
        validadesL = new ArrayList<>();
        }

    /**
     * GET devolve o produto
     * @return produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * GET devolve a quantidade de total retirada do stock
     * @return total (quantidade)
     */
    public int getQuantidade() {
        int total = 0;
		for ( int i : quantidadesL) {
			total += i;
		}
		return total;
    }

    /**
     * GET devolve o preço unitario
     * @return precoUnitario
     */
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Calcula o subtotal deste item (preço x quantidade)
     * @return valor(precoUnitario * quantidadeT)
     */
    public double calcularSubtotal() {
        return precoUnitario * quantidadeT;
    }
    
    /**
     *Reduz o stock do produto pela quantidade indicada, registando
     *os lotes e validades afetados nas listas internas.
     *Deve ser chamado quando a reserva é confirmada.
     * @param quantidade - quantidade a retirar do stock
     */
    public void registarStockeVal(int quantidade) {
    	Produto p =getProduto();
		p.reduzirComRegisto(quantidade, quantidadesL, validadesL);
		}
    
    /**
     * repoe no stock do produto as quantidades e validades previamente registradas.
     * Deve ser chamado quando a reserva for cancelada.
     */
    public void repor() {
    	Produto p = getProduto();
    	p.restituirStock(quantidadesL, validadesL);
    }

    /**
     *toString - devolve as informaçoes do item 
     */
    @Override
    public String toString() {
        return "  - " + produto.getNome() + " x" + getQuantidade() + " @ " + precoUnitario + "€ = " + calcularSubtotal() + "€";
    }

	
}