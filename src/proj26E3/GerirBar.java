package proj26E3;

import java.util.ArrayList;
import java.util.Scanner;

public class GerirBar {
	private ArrayList<Utilizador> funcionarios;
	private ArrayList<Cliente> clientes;
	private ArrayList<Produto> produtos;
	
	public GerirBar() {
		funcionarios = new ArrayList<>();
		clientes = new ArrayList<>();
	}
	
	
	public boolean autenticarPorFuncionario(int num, String chave) {
		if (funcionarios.isEmpty()) {
			return false;
		}
		for (Utilizador f: funcionarios) {
			if (f.getNumero() == num && f.getPw().equals(chave)){
				System.out.print("Utilizador encontrado!");
				return true;
			}
		}
		return false;
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
	
//--------------------------------------------------------------------
		/*
		 * US02: Registar Pedido 
		 * O funcionário introduz o ID do produto e a quantidade. 
		 * O sistema valida o stock, reduz o stock e calcula o total.
		 */
		public void registrarPedido() {
			Scanner sc = new Scanner(System.in);
			System.out.println("--- REGISTAR NOVO PEDIDO ---");
			
			System.out.print("Introduza o ID do produto: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			Produto p = pesquisarProduto(id);
			
			if (p == null) {
				System.out.println("Produto não encontrado!");
				return;
			}
			
			// Validação de Stock (usando a estrutura de lotes)
			if (p.getStock().isEmpty() || p.getStock().get(0).intValue() <= 0) {
				System.out.println("Erro: Produto sem stock disponível ou fora da validade!");
				return;
			}
			
			System.out.print("Introduza a quantidade desejada: ");
			int qtd = sc.nextInt();
			sc.nextLine();
			
			int stockAtual = p.getStock().get(0).intValue();
			if (qtd > stockAtual) {
				System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
				return;
			}
			
			// Atualizar o stock (reduzir a quantidade no lote 0)
			p.getStock().set(0, stockAtual - qtd);
			
			double totalItem = p.getPreco() * qtd;
			System.out.println("\nPedido registado com sucesso!");
			System.out.println("Produto: " + p.getNome() + " x" + qtd);
			System.out.println("Total a pagar: " + totalItem + "€");
		}

//--------------------------------------------------------------------
		/*
		 * US09: Consultar Reservas Pendentes - INCOMPLETA PRECISA DA PARTE DO TOMAS
		 * Mostra as pré-reservas feitas pelos clientes que ainda não foram levantadas.
		 */
		public void consultarReservasPendentes() {
			System.out.println("--- CONSULTAR RESERVAS PENDENTES ---");
			//System.out.println(" À procura de reservas com EstadoReserva.PENDENTE...");
			//System.out.println("Não existem reservas pendentes para apresentar de momento.");
		}

		//--------------------------------------------------------------------
		/*
		 * US09: Confirmar Reserva INCOMPLETA PRECISA DA PARTE DO TOMAS
		 * O funcionário aceita a reserva pendente, o que faz o stock ser finalmente decrementado.
		 */
		public void confirmarReserva() {
			Scanner sc = new Scanner(System.in);
			System.out.println("--- CONFIRMAR RESERVA ---");
			
			System.out.print("Introduza o ID da Reserva a confirmar: ");
			int idReserva = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Reserva " + idReserva + " não encontrada (Modo de simulação ativo).");
			
			/* * NOTA DE INTEGRAÇÃO (Tomás e António):
			 * O código final fará algo como:
			 * * Reserva r = pesquisarReserva(idReserva);
			 * if (r != null && r.getEstado() == EstadoReserva.PENDENTE) {
			 * r.setEstado(EstadoReserva.CONFIRMADA);
			 * // Bloquear/reduzir o stock dos produtos que estão dentro da reserva
			 * System.out.println("Reserva confirmada com sucesso! Stock atualizado.");
			 * }
			 */
		}
		
		public void addFuncionario(Utilizador f) {
			funcionarios.add(f);
		}
}
