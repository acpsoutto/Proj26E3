package proj26E3;

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

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        int total = 0;
		for ( int i : quantidadesL) {
			total += quantidadesL.get(i);
		}
		return total;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Calcula o subtotal deste item (preço x quantidade)
     */
    public double calcularSubtotal() {
        return precoUnitario * quantidadeT;
    }
    
    public void registarStockeVal(int quantidade) {
    	Produto p =getProduto();
		p.reduzirComRegisto(quantidade, quantidadesL, validadesL);
		}
    
    public void repor() {
    	Produto p = getProduto();
    	p.restituirStock(quantidadesL, validadesL);
    }

    @Override
    public String toString() {
        return "  - " + produto.getNome() + " x" + getQuantidade() + " @ " + precoUnitario + "€ = " + calcularSubtotal() + "€";
    }

	
}