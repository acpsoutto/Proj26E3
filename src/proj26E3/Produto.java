package proj26E3;

/**
 * Representa um produto do bar, com id, nome, preço, categoria.
 */
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
	 * @param id - identificador do produto
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
	 * GET devolve o preço do produto
	 * @return the preco - preco do produto
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * GET altera o preço dos produtos
	 * @param preco - guarda o novo preço do produto
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * GET devolve id do produto
	 * @return the id - id do produto
	 */
	public int getId() {
		return id;
	}

	/**
	 * GET devolve o nome do produto
	 * @return the nome - nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * GET devolve a categoria do produto
	 * @return the categoria - categoria do produto
	 */
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	
	/**
	 * GET devolve a quantidade de produto no stock 
	 * @return total - total de quantidade no stock
	 */
	public int getStock() {
	    int total = 0;
	    for (int valor : stock) {
	        total += valor;      
	    }
	    return total;
	}
	

	/**
	 * Adiciona no stock um novo lote
	 * @param s - stock do novo lote
	 * @param val - meses de valiade do novo lote
	 */
	public void adicionarStock(int s, int val) {
		YearMonth novaValidade = YearMonth.now().plusMonths(val);
	    for (int i = 0; i < validades.size(); i++) {
	        if (validades.get(i).equals(novaValidade)) {
	            stock.set(i, stock.get(i) + s);
	            return;
	        }
	    }
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
	

	/**
	 * Atualiza o preço do produto.
	 * Se novoPreco <= 0 ele não atualiza o preço.
	 * Se novoPreco > 0 ele atualiza o preço.
	 * @param novoPreco - novo preço do produto
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

	/**
	 * Reduz no stock a quantidade de itens desejado
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
	
	/**
	 * Reduz com registro - é utilizada para as reservas
	 * @param qtd - quantidade do stock
	 * @param quantidadesRetiradas - quantidade que a reserva pede
	 * @param validadesRetiradas - validade de cada respectivo lote 
	 */
	public void reduzirComRegisto(int qtd, ArrayList<Integer> quantidadesRetiradas, ArrayList<YearMonth> validadesRetiradas ) {
		int i = 0;
		do {
			int lote = stock.get(i);
			YearMonth valid = validades.get(i);
			if(lote >= qtd) {
				stock.set(i, lote - qtd);
				quantidadesRetiradas.add(qtd);
	            validadesRetiradas.add(valid);

				qtd = 0;
			}else {
				quantidadesRetiradas.add(qtd);
	            validadesRetiradas.add(valid);
	            
				qtd -= lote;
				stock.set(i, 0);
			}
			
			if(stock.get(i) == 0) {
				stock.remove(i);
				validades.remove(i);
			}
		}while(qtd > 0);
	}
	
	/**
	 * Restaura o stock 
	 * @param quantidadesL - quantidade do lote a ser retornada
	 * @param validadesL - validade do lote a ser retornada
	 */
	public void restituirStock(ArrayList<Integer> quantidadesL, ArrayList<YearMonth> validadesL) {
		for (int i = 0; i < quantidadesL.size(); i++) { 

	        int quantidade = quantidadesL.get(i);
	        YearMonth validade = validadesL.get(i);
	        boolean encontrado = false;

	        for (int j = 0; j < validades.size(); j++) {
	            if (validades.get(j).equals(validade)) {
	                stock.set(j, stock.get(j) + quantidade);
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) {
	            int pos = 0;
	            while (pos < validades.size() && validades.get(pos).isBefore(validade)) {
	                pos++;
	            }
	            validades.add(pos, validade);
	            stock.add(pos, quantidade);
	        }
	    }
	}

	
	/**
	 *toString - devolve as informações do produto
	 */
	@Override
	public String toString() {
		return "Produto| " + id + " - " + nome + " - "+categoria+" - preco:" + preco + " - Lotes = "+stock+ " - validade respetivo stock = "+ validades;
	}





}
