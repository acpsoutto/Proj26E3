package proj26E3;

public class Elementar extends Produto{
	private double precoE;

	/**
	 * @param id - id do produto elementar
	 * @param nome - nome do produto elementar
	 * @param precoE - preço do produto elementar
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
		return super.toString()+ " | Preço: " + precoE + " ";
	}
}
