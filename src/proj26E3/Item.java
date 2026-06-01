package proj26E3;

import java.util.ArrayList;

public class Item {
	private int qtd;
	private Produto produto;
	private ArrayList<Lote> itensReservados;
	
	public Item(int qtd, Produto produto) {
		this.qtd = qtd;
		this.produto = produto;
	}

	public int getQtd() {
		return qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public ArrayList<Lote> getItensReservados() {
		return itensReservados;
	}
	
	//public void reduzir() { //reuzircomregisto
	//	produto.consumirQuantidade(qtd);
	//}
	
	public void registarStockVal(int quantidade) {
		ArrayList<Lote> retirados = produto.retirarComRegisto(quantidade);
		itensReservados.addAll(retirados);
	}
	
	public void repor() {
		produto.restituirStock(itensReservados);
		itensReservados = new ArrayList<>();
	}
	
	public double calcularSubtotal() {
	    return produto.getPreco() * qtd;
	}

	@Override
	public String toString() {
		return "Item : "+ produto.getNome() 
		+"[qtd=" + qtd 
		+ ", produto=" + produto 
		+ ", ItensReservados=" + itensReservados + "]";
	}
	
	

}
