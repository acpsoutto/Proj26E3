package proj26E3;

public class ParcelaUsada {
	private Produto P;
	private double qtd;
	/**
	 * @param p - qual é o produto
	 * @param qtd - quantidade do produto usado no complementar
	 */
	public ParcelaUsada(Produto p, double qtd) {
		P = p;
		this.qtd = qtd;
	}
	
	public Produto getP() {
		return P;
	}
	
	public double getQtd() {
		return qtd;
	}

	

}
