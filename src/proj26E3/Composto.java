package proj26E3;

import java.time.YearMonth;
import java.util.ArrayList;

public class Composto extends Produto{
	private ArrayList<ParcelaUsada> parcelas;
	private double precoC;
	
	/**
	 * @param id
	 * @param nome
	 * @param validade
	 * @param qtd
	 * @param parcelas
	 * @param precoC
	 */
	public Composto(int id, String nome, double precoC) {
		super(id, nome);
		this.precoC = precoC;
		parcelas = new ArrayList<>();
	}
	
	@Override
	public double getPreco() {
		return precoC;
	}

	@Override
	public void setPreco(double preco) {
		this.precoC = preco;
	}
	
	public void adicionarNoComposto(Produto p, double qtd) {
		ParcelaUsada n = new ParcelaUsada(p, qtd);
		parcelas.add(n);
		System.out.println("Parcela adicionada");
	}

	public boolean getSize() {
		if(parcelas.size()< 2) {
			return false;
		}
		return true;
	}

	public boolean jaExiste(Produto p) {
		if(parcelas.isEmpty()) {
			return true;
		}
		for(ParcelaUsada u : parcelas) {
			if(u.getP() == p) { 
				return false;
			}
		}
		return true;
	}
	
	public int imprimirParcelas() {
		int max = Integer.MAX_VALUE;
		
		for(ParcelaUsada p : parcelas) {
			Produto po = p.getP();
			System.out.println(po.getNome()+ " - " + p.getQtd());
			double qtdNecessaria = p.getQtd();
			double stockTotal = po.getStock();
			int poss = (int)(stockTotal/qtdNecessaria);
			if(poss < max) {
				max = poss;
			}
		}
		return max;
	}
	
	public void produzir(int quantidade) {
	    for (int i = 0; i < quantidade; i++) {
	        YearMonth validadeFinal = null;
	        for (ParcelaUsada parcela : parcelas) {
	            Produto p = parcela.getP();
	            double qtdNecessaria = parcela.getQtd();
	            YearMonth validadeIngrediente = p.consumirQuantidade(qtdNecessaria);
	            if (validadeFinal == null || validadeIngrediente.isBefore(validadeFinal)) {
	                validadeFinal = validadeIngrediente;
	            }
	        }
	        adicionarStock(validadeFinal, 1);
	    }
	}
		
	@Override
	public String toString() {
		return super.toString()+ "precoE=" + precoC + " ";
	}

}
