package proj26E3;

import java.time.YearMonth;
import java.util.ArrayList;

public class Composto extends Produto{
	private ArrayList<ParcelaUsada> parcelas;
	private double precoC;
	
	/**
	 * @param id - número de identificação do produto composto
	 * @param nome - nome do produto composto
	 * @param parcelas - constituintes e respetivas quantidades do produto composto
	 * @param precoC - preço do produto composto
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
	
	/**
	 * Adiciona uma parcela no produto composto
	 * @param p - parcela que compõem o produto composto
	 * @param qtd  - quantidade de parcela usada neste produto composto
	 */
	public void adicionarNoComposto(Produto p, double qtd) {
		ParcelaUsada n = new ParcelaUsada(p, qtd);
		parcelas.add(n);
		System.out.println("Parcela adicionada!");
	}
	
	/**
	 * Verifica se o composto é constituido por 2 ou mais parcelas
	 * @return true se for 
	 * @return false se não for
	 */
	public boolean getSize() {
		if(parcelas.size()< 2) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se uma certa parcela já foi usada no composto
	 * @param p - parcela
	 * @return true se a parcela ainda nao foi usada ou se o composto não tem nenhuma parcela
	 * @return false se a parcela ja foi usada
	 */
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
	
	/**
	 * Imprime as parcelas que constituem o composto e as quantidades usadas de cada uma
	 * @return max - maximo possivel de compostos a adicionar em stock
	 */
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
	
	/**
	 * Retira quantidades de parcelas para fazer compostos
	 * @param quantidade - quantidades de composto a serem feitas
	 */
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
		return super.toString()+ "Preço: " + precoC + " ";
	}

}
