package proj26E3;

import java.util.ArrayList;

public class Item {
	private int qtd;
	private Produto produto;
	private ArrayList<Lote> itensReservados;
	
	public Item(int qtd, Produto produto) {
		this.qtd = qtd;
		this.produto = produto;
		itensReservados = new ArrayList<>();
	}

	public int getQtd() {
		return qtd;
	}
	
	public void setQtd(int quant) {
		this.qtd = quant;
	}

	public Produto getProduto() {
		return produto;
	}

	public ArrayList<Lote> getItensReservados() {
		return itensReservados;
	}
	
	/**
	 * Regista a validade e quantidade dos produtos retirados de stock
	 * @param quantidade
	 */
	public void registarStockVal(int quantidade) {
		ArrayList<Lote> retirados = produto.retirarComRegisto(quantidade);
		itensReservados.addAll(retirados);
	}
	
	/**
	 * Retorna os itens reservados de volta a stock em caso de cancelamento de reserva
	 */
	public void repor() {
		produto.restituirStock(itensReservados);
		itensReservados = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return total - custo de cada produto * a sua quantidade
	 */
	public double calcularSubtotal() {
	    return produto.getPreco() * qtd;
	}

	@Override
	public String toString() {
		return produto.getNome() 
		+ " [Quantidade:" + qtd + "]" ;
	}
	
	

}
