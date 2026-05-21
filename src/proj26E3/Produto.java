package proj26E3;

import java.time.YearMonth;
import java.util.ArrayList;

public class Produto {
	private int id;
	private String nome;
	private double preco;
	private ArrayList<YearMonth> validades;
	private CategoriaProduto categoria;
	private ArrayList<Integer> stock; 
	
	/**
	 * CONSTRUTOR
	 * @param id - id do produto
	 * @param nome - nome do produto
	 * @param preco - preço do produto
	 * @param validade - meses de validade do produto 
	 * @param categoria - categoria do produto (Elementar, Composto, Bebida)
	 * @param quantidadestock - quantidade do produto em stock
	 */
	public Produto(int id, String nome, double preco, CategoriaProduto categoria, int quantidadeslot, int validade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		stock = new ArrayList<>();
		stock.add(quantidadeslot);
		validades = new ArrayList<>();
		validades.add(YearMonth.now().plusMonths(validade));
		
	}
	
	/**
	 * @return the preco - preco do produto
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco - guarda o novo preço do produto
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the id - id do produto
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nome - nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the categoria - categoria do produto
	 */
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	
	public int getStock() {
		int total = 0;
		for ( int i : stock) {
			total += stock.get(i);
		}
		return total;
	}
	
	/*
	 * @param s - stock do novo lote
	 * @param val - meses de valiade do novo lote
	 */
	public void adicionarStock(int s, int val) {
		stock.add(s);
		validades.add(YearMonth.now().plusMonths(val));
		
		for(int i = 0; i < validades.size() -1; i++) {
			for(int j = i+1; j < validades.size();j++) {
				if(validades.get(i).isAfter(validades.get(j))) {
					YearMonth temp= validades.get(i);
					int tempStock = stock.get(i);
					
					validades.set(i, validades.get(j));
					stock.set(i, stock.get(j));
					
					validades.set(j, temp);
					stock.set(j, tempStock);
				}
			}
		}
	}
	
	/*
	 * Atualiza o preço do produto.
	 * Se novoPreco <= 0 ele não atualiza o preço.
	 * Se novoPreco > 0 ele atualiza o preço.
	 */	
	public void atualizarPreco(double novoPreco) {
		if(novoPreco <= 0 ){
			System.out.println("Preço Invalido! O novo preço inserido é igual ou inferior 0 Tente Novamente.");
			System.out.println("Preco de "+getNome()+" manten-se de: "+getPreco());
			return;
		}
		setPreco(novoPreco);
		System.out.println("Preço de "+getNome()+ " atualizado agora é: "+getPreco());
	}
	
	/*
	 * @param redu - quantidade de itens a reduzir
	 */
	public void reduzirStock(int redu) {
		int i = 0;
		do {
			int lote = stock.get(i);
			if(lote >= redu) {
				stock.set(i, lote - redu);
				redu = 0;
			}else {
				redu -= lote;
				stock.set(i, 0);
			}
			
			if(stock.get(i) == 0) {
				stock.remove(i);
				validades.remove(i);
			}
		}while(redu > 0);
	}
	
	
	@Override
	public String toString() {
		return "Produto| " + id + " - " + nome + " - "+categoria+" - preco:" + preco + " - Lotes = "+stock+ " - validade respetivo stock = "+ validades;
	}





}
