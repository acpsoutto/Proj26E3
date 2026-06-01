package proj26E3;

import java.time.YearMonth;

public class Lote {
	private YearMonth validade;
	private double quantidade;
	
	/**
	 * @param validade - validade do lote
	 * @param quantidade - quantidade do produto no lote
	 */
	public Lote(YearMonth validade, double quantidade) {
		this.validade = validade;
		this.quantidade = quantidade;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public YearMonth getValidade() {
		return validade;
	}

	@Override
	public String toString() {
		return "[quantidade: "+ quantidade + " | validade: "+ validade + "]";
	}
}
