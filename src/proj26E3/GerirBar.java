package proj26E3;

import java.util.ArrayList;

public class GerirBar {
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Cliente> clientes;
	private ArrayList<Produto> produtos;
	
	public GerirBar() {
		funcionarios = new ArrayList<>();
		clientes = new ArrayList<>();
	}
	
	
	
	
	
	/*
	 * Metodos relacionados com as funções do gerente
	 */
	public Produto pesquisarProduto(int id) {
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

//--------------------------------------------------------------------
	/*
	 *US06:Consultar produtos disponíveis (Stock > 0 e dentro da validade)
	 */
	public void consultarProdutosDisponiveis() {
		System.out.println("\n----- PRODUTOS DISPONIVEIS -----");
		boolean encontrou = false;
		
		for (Produto p: produtos) {
			if (!p.getStock().isEmpty() && p.getStock().get(0) > 0) {
				System.out.println("|ID: " +p.getId() 
						+ "\n|Nome: " + p.getNome() 
						+ "\n|Categoria: " +p.getCategoria() 
						+ "\n|Preço: " + p.getPreco() + "€");
			    encontrou =true;
			}
		}
		if (!encontrou) {
			System.out.println("Não existem produtos disponiveis no stck este momento!");
		}
	}
	
	
}
