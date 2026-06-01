package proj26E3;

public class Elementar extends Produto{
	private double precoE;

	/**
	 * @param id - id do produto
	 * @param nome - nome do produto
	 * @param validade - validade do lote quando o produto é adicionado
	 * @param qtd - quantidade do lote quando o produto é adicionado
	 * @param precoE - preço do produto
	 */
	public Elementar(int id, String nome, double precoE) {
		super(id, nome);
		this.precoE = precoE;
	}
	
	@Override
	public double getPreco() {
		return precoE;
	}
	
	@Override
	public void setPreco(double preco) {
		this.precoE = preco;
	}

	@Override
	public String toString() {
		return super.toString()+ "precoE=" + precoE + " ";
	}
	
	
	
	
	
	

}
