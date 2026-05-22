package proj26E3;

import java.util.ArrayList;
import java.util.Scanner;

public class GerirBar {
	private ArrayList<Utilizador> utilizadores;
	private ArrayList<Produto> produtos;
	private ArrayList<Reserva> reservas; // ADICIONADO: lista de reservas (Tomás)
	
	public GerirBar() {
		utilizadores = new ArrayList<>();
		produtos = new ArrayList<>();
		reservas = new ArrayList<>(); // ADICIONADO: inicialização da lista de reservas (Tomás)
	}
	
	
	public boolean autenticarPorFuncionario(int num, String chave) {
		if (utilizadores.isEmpty()) {
			return false;
		}
		for (Utilizador f: utilizadores) {
			if (f.getNumero() == num && f.getPw().equals(chave)){
				System.out.print("Utilizador encontrado!");
				return true;
			}
		}
		return false;
	}
	
	public Utilizador pesquisarUtilizador(int num) {
		if (utilizadores.isEmpty()) {
			return null;
		}
		for (Utilizador u : utilizadores) {
			if (u.getNumero()== num) {
				return u;
			}
		}
		return null;
	}
	
	public void adicionarUtilizador(int num, String nome, String pw, String mail, TipoUtilizador tipo) {
		if (tipo == TipoUtilizador.ADMNISTRACAO || tipo== TipoUtilizador.GERENTE) {
			Utilizador u = new Utilizador (num, nome, pw, mail, tipo);
			utilizadores.add(u);
		}
		if (tipo == TipoUtilizador.FUNCIONARIO_BAR) {
			FuncionarioBar f = new FuncionarioBar (num, nome, pw, mail, tipo);
			utilizadores.add(f);
		}
		if (tipo == TipoUtilizador.CLIENTE) {
			Cliente c = new Cliente (num, nome, pw, mail, tipo);
			utilizadores.add(c);
		}
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
	
//---------------------------------------------------------------------
	
	/*
	 * METODO PARA REDUZIR O STOCK COM VENDAS
	 * 
	 */
	public void reduzirStock(int id, int quant) {
		Produto p = pesquisarProduto(id);
		p.reduzirStock(quant);
	}

//--------------------------------------------------------------------
	/*
	 * Consultar produtos disponíveis (Stock > 0)
	 */
	public void consultarProdutosDisponiveis() {
		System.out.println("\n----- PRODUTOS DISPONIVEIS -----");
		boolean encontrou = false;
		
		for (Produto p: produtos) {
			if (!(p.getStock()==0)) {
				System.out.println("|ID: " +p.getId() 
						+ "\n|Nome: " + p.getNome() 
						+ "\n|Categoria: " +p.getCategoria() 
						+ "\n|Preço: " + p.getPreco() + "€");
			    encontrou =true;
			}
		}
		if (!encontrou) {
			System.out.println("Não existem produtos disponiveis no stock este momento!");
		}
	}
	
//--------------------------------------------------------------------
		/*
		 * US02: Registar Pedido 
		 */
		public void registrarPedido(Scanner sc) {
			System.out.println("--- REGISTAR NOVO PEDIDO ---");
			
			System.out.print("Introduza o ID do produto: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			Produto p = pesquisarProduto(id);
			
			if (p == null) {
				System.out.println("Produto não encontrado!");
				return;
			}
			
			if (p.getStock()==0 ) {
				System.out.println("Erro: Produto sem stock disponível ou fora da validade!");
				return;
			}
			
			System.out.print("Introduza a quantidade desejada: ");
			int qtd = sc.nextInt();
			sc.nextLine();
			
			int stockAtual = p.getStock();
			if (qtd > stockAtual) {
				System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
				return;
			}
			
			p.reduzirStock(stockAtual - qtd);
			
			double totalItem = p.getPreco() * qtd;
			System.out.println("\nPedido registado com sucesso!");
			System.out.println("Produto: " + p.getNome() + " x" + qtd);
			System.out.println("Total a pagar: " + totalItem + "€");
		}

//--------------------------------------------------------------------
		/*
		 * ADICIONADO (Tomás): Pesquisa uma reserva pelo seu ID
		 * Percorre a lista de reservas e devolve a que tiver o id correspondente.
		 * Devolve null se não encontrar nenhuma.
		 */
		public Reserva pesquisarReserva(int id) {
			for (Reserva r : reservas) {
				if (r.getId() == id) {
					return r;
				}
			}
			return null;
		}

//--------------------------------------------------------------------
		/*
		 * ADICIONADO (Tomás): US06 - Fazer Pré-Reserva
		 * O cliente seleciona produtos disponíveis e submete uma pré-reserva.
		 * A reserva fica no estado PENDENTE até ser confirmada por um funcionário.
		 */
		public void fazerPreReserva(Scanner sc) {
			System.out.println("--- FAZER PRÉ-RESERVA ---");

			// Mostra os produtos disponíveis para o cliente escolher
			consultarProdutosDisponiveis();

			// Gera um ID único para a reserva (tamanho da lista + 1)
			int novoId = reservas.size() + 1;
			Reserva novaReserva = new Reserva(novoId);

			// Loop para adicionar vários produtos à reserva
			String continuar = "s";
			while (continuar.equalsIgnoreCase("s")) {
				System.out.print("Introduza o ID do produto que quer reservar: ");
				int idProduto = sc.nextInt();
				sc.nextLine();

				Produto p = pesquisarProduto(idProduto);

				if (p == null) {
					System.out.println("Produto não encontrado! Tente novamente.");
				} else if (p.getStock() == 0) {
					System.out.println("Produto sem stock disponível! Tente outro.");
				} else {
					System.out.print("Introduza a quantidade: ");
					int qtd = sc.nextInt();
					sc.nextLine();

					if (qtd <= 0) {
						System.out.println("Quantidade inválida!");
					} else if (qtd > p.getStock()) {
						System.out.println("Stock insuficiente! Stock disponível: " + p.getStock());
					} else {
						// Adiciona o item à reserva
						novaReserva.adicionarItem(p, qtd);
						System.out.println("Item adicionado: " + p.getNome() + " x" + qtd);
					}
				}

				System.out.print("Deseja adicionar mais produtos? (s/n): ");
				continuar = sc.nextLine();
			}

			// Só guarda a reserva se tiver pelo menos um item
			if (novaReserva.getItensR().isEmpty()) {
				System.out.println("Reserva cancelada — nenhum item foi adicionado.");
			} else {
				reservas.add(novaReserva);
				System.out.println("\nPré-reserva submetida com sucesso!");
				System.out.println("ID da sua reserva: " + novaReserva.getId());
				System.out.println("Total estimado: " + novaReserva.calcularTotal() + "€");
				System.out.println("Estado: " + novaReserva.getEstado());
			}
		}

//--------------------------------------------------------------------
		/*
		 * COMPLETO (Tomás): US09 - Consultar Reservas Pendentes
		 * Percorre a lista de reservas e mostra apenas as que estão no estado PENDENTE.
		 */
		public void consultarReservasPendentes() {
			System.out.println("--- CONSULTAR RESERVAS PENDENTES ---");
			boolean encontrou = false;

			for (Reserva r : reservas) {
				// Só mostra as reservas que ainda estão pendentes
				if (r.getEstado() == EstadoReserva.PENDENTE) {
					System.out.println(r);
					encontrou = true;
				}
			}

			if (!encontrou) {
				System.out.println("Não existem reservas pendentes de momento.");
			}
		}

//--------------------------------------------------------------------
		/*
		 * COMPLETO (Tomás): US09 - Confirmar Reserva
		 * O funcionário procura a reserva pelo ID e confirma-a.
		 * Ao confirmar, o stock dos produtos é reduzido.
		 */
		public void confirmarReserva(Scanner sc) {
			System.out.println("--- CONFIRMAR RESERVA ---");

			System.out.print("Introduza o ID da Reserva a confirmar: ");
			int idReserva = sc.nextInt();
			sc.nextLine();

			// Procura a reserva na lista
			Reserva r = pesquisarReserva(idReserva);

			if (r == null) {
				System.out.println("Reserva " + idReserva + " não encontrada.");
				return;
			}

			if (r.getEstado() != EstadoReserva.PENDENTE) {
				System.out.println("Esta reserva não pode ser confirmada. Estado atual: " + r.getEstado());
				return;
			}

			// Confirma a reserva e reduz o stock de cada produto reservado
			r.confirmar();
			for (ItemReserva item : r.getItensR()) {
				item.getProduto().reduzirStock(item.getQuantidade());
			}
			System.out.println("Stock atualizado com sucesso!");
		}
}