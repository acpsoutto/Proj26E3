package proj26E3;

import java.util.ArrayList;

public class GerirBar {
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Cliente> clientes;
	private ArrayList<Produto> produtos;
	
	public GerirBar() {
		funcionarios = new ArrayList<>();
		clientes = new ArrayList<>();
		produtos = new ArrayList<>();
	}
	
	
	
	
	
	/*
	 * Metodos relacionados com as funções do gerente
	 */
	public Produto pesquisarProduto(int id) {
		if(produtos.isEmpty()){
			return null;
		}
		for(Produto p : produtos ) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void adicionarProduto(int id, String nome, double preco, CategoriaProduto categoria, int stock, int validade) {
		Produto p = new Produto(id, nome, preco, categoria, stock, validade);
		produtos.add(p);
		System.out.println("Produto adicionado");
	}

	public void imprimirProdutos() {
		for(Produto p : produtos) {
			System.out.println(p);
		}
	}

	public void imprimirPreços() {
		for(Produto p : produtos){
			System.out.println("Produto: " +p.getId()+ " | "+p.getNome()+" Preço="+p.getPreco());
		}
		
	}

	public void atualizarPreco(int id, double preco) {
		Produto p = pesquisarProduto(id);
		p.atualizarPreco(preco);
	}

	public void adicionarStock(int id, int quant, int val) {
		Produto p = pesquisarProduto(id);
		p.adicionarStock(quant, val);
	}

	/*
	 * METODO PARA REDUZIR O STOCK COM VENDAS
	 * @param quant - quantidade de itens a ser reduzido do stock
	 * @param id - id do produto
	 */
	public void reduzirStock(int id, int quant) {
		Produto p = pesquisarProduto(id);
		p.reduzirStock(quant);
	}

	
	
	
	
}
