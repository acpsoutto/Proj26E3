package proj26E3;

/**
 * Representa um produto do bar, com id, nome, preço, categoria.
 */
import java.time.YearMonth;
import java.util.ArrayList;

public class Produto {
	private int id;
	private String nome;
	private ArrayList<Lote> lotes;
	
	/**
	 * CONSTRUTOR
	 * @param id - identificador do produto
	 * @param nome - nome do produto
	 * @param preco - preço do produto
	 * @param validade - meses de validade do produto 
	 * @param categoria - categoria do produto (Elementar, Composto, Bebida)
	 * @param quantidadestock - quantidade do produto em stock
	 */

	public Produto(int id, String nome) {
		this.id = id;
		this.nome = nome;
		lotes = new ArrayList<>();
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
	
	public double getPreco() {
		return 0;
	}
	
	public void setPreco(double preco) {
	}
	

	/**
	 * GET devolve a quantidade de produto no stock 
	 * @return total - total de quantidade no stock
	 */
	public int getStock() {
	    int total = 0;
	    for (Lote l : lotes) {
	        total += l.getQuantidade();      
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
		for(Lote l : lotes) {
			if(l.getValidade().equals(novaValidade)) {
				l.setQuantidade(l.getQuantidade()+s);
				return;
			}
		}
		Lote l = new Lote(novaValidade, s);
		lotes.add(l);
		
		lotes.sort((l1,l2) -> l1.getValidade().compareTo(l2.getValidade()));
	}
	
	/**
	 * Adiciona no stock um novo lote de um produto composto
	 * @param val - validade do produto
	 * @param s - quantidade do produto
	 */
	public void adicionarStock(YearMonth val, int s) {
		for(Lote l : lotes) {
			if(l.getValidade().equals(val)) {
				l.setQuantidade(l.getQuantidade()+s);
				return;
			}
		}
		Lote l = new Lote(val, s);
		lotes.add(l);
		
		lotes.sort((l1,l2) -> l1.getValidade().compareTo(l2.getValidade()));
	}
	

	/**
	 * Reduz no stock a quantidade de itens desejado.
	 * Este metodo é usada exclusivamente para adiconar ao Stock de produtos compostos reduzindo as suas parcelas
	 * @param redu - quantidade de itens a reduzir
	 */
	public YearMonth consumirQuantidade(double qtdNecessaria) {
	    lotes.sort((l1, l2) -> l1.getValidade().compareTo(l2.getValidade()));
	    YearMonth validadeUsada = null;
	    int i = 0;
	    while (qtdNecessaria > 0 && i < lotes.size()) {
	        Lote l = lotes.get(i);
	        if (validadeUsada == null) {
	            validadeUsada = l.getValidade();
	        }
	        
	        double qtdLote = l.getQuantidade();
	        if (qtdLote <= qtdNecessaria) {
	            qtdNecessaria -= qtdLote;
	            lotes.remove(i);
	        } else {
	            l.setQuantidade(qtdLote - qtdNecessaria);
	            qtdNecessaria = 0;
	        }
	    }
	    return validadeUsada;
	}
	
	public void reduzir(int quant) {
	    consumirQuantidade(quant);
	}
	
	public ArrayList<Lote> retirarComRegisto(double qtd) {
	    ArrayList<Lote> retirados = new ArrayList<>();
	    int i = 0;
	    while (qtd > 0 && i < lotes.size()) {
	        Lote l = lotes.get(i);
	        double qtdLote = l.getQuantidade();
	        if (qtdLote <= qtd) {
	            retirados.add(new Lote(l.getValidade(),qtdLote));
	            qtd -= qtdLote;
	            lotes.remove(i);
	        } else {
	            retirados.add(new Lote(l.getValidade(),qtd));
	            l.setQuantidade(qtdLote - qtd);
	            qtd = 0;
	        }
	    }
	    return retirados;
	}
	
	/**
	 * Restaura o stock 
	 * 
	 */
	public void restituirStock(ArrayList<Lote> devolvidos) {
	    for (Lote devolvido : devolvidos) {
	        boolean encontrado = false;
	        for (Lote l : lotes) {
	            if (l.getValidade().equals(devolvido.getValidade())) {
	                l.setQuantidade( l.getQuantidade()+ devolvido.getQuantidade() );
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) {
	            lotes.add(new Lote(devolvido.getValidade(), devolvido.getQuantidade())
	            );
	        }
	    }
	    lotes.sort((l1, l2) -> l1.getValidade().compareTo(l2.getValidade()));
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", lotes=" + lotes + "]";
	}


}
