package proj26E3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GerirBar {
	private ArrayList<Utilizador> utilizadores;
	private ArrayList<Produto> produtos;
	
	public GerirBar() {
		utilizadores = new ArrayList<>();
		produtos = new ArrayList<>();

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
	
	public void adicionarUtilizador(int num, String nome, String mail, String pw, TipoUtilizador tipo) {
		if (tipo == TipoUtilizador.ADMNISTRACAO || tipo== TipoUtilizador.GERENTE) {
			Utilizador u = new Utilizador (num, nome,mail, pw, tipo);
			utilizadores.add(u);
		}
		if (tipo == TipoUtilizador.FUNCIONARIO_BAR) {
			FuncionarioBar f = new FuncionarioBar (num, nome,mail, pw, tipo);
			utilizadores.add(f);
		}
		if (tipo == TipoUtilizador.CLIENTE) {
			Cliente c = new Cliente (num, nome,mail, pw, tipo);
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
	
	public boolean verificarStock(int id, int qtd) {
		Produto p = pesquisarProduto(id);
		if(p.getStock() < qtd) {
			return false;
		}
		return true;
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
	public boolean consultarProdutosDisponiveis() {
		System.out.println("\n----- PRODUTOS DISPONIVEIS -----");
		boolean encontrou = false;
		for (Produto p: produtos) {
			if (p.getStock()!=0) {
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
		return encontrou;
	}
	
//--------------------------------------------------------------------
		/*
		 * Registar Pedido 
		 */
		public Pedido registrarPedido(int numU, int idPedido) {
			Utilizador u = pesquisarUtilizador(numU);
			FuncionarioBar f = (FuncionarioBar) u;
			Pedido pd = new Pedido (idPedido);
			f.adicionarPedido(pd);
			return pd;
		}

		public void adicionarNoPedido(int idP, int qtd, Pedido pd) {
			Produto p = pesquisarProduto(idP);
			pd.adicionarItem(p, qtd);
		}
		
		public void apagarPedido(int idPedido, int uti) {
			Utilizador u = pesquisarUtilizador(uti);
			FuncionarioBar fb = (FuncionarioBar) u;
			fb.apagarPedido(idPedido);
		}
		
//--------------------------------------------------------------------
		/*
		 * Pesquisa uma reserva pelo seu ID
		 * Percorre a lista de reservas e devolve a que tiver o id correspondente.
		 * Devolve null se não encontrar nenhuma.
		 */
		public Reserva pesquisarReserva(int id) {
			Reserva r;
			for(Utilizador u : utilizadores) {
				if(u instanceof Cliente) {
					Cliente c = (Cliente) u;
					if (c.encontrarReserva(id) != null) {
						r = c.encontrarReserva(id);
						return r;
					}
				}
			}
			return null;
		}
		
		public boolean detetarEstado(int id) {
			Reserva r = pesquisarReserva(id);
			if(r.getEstado()!= EstadoReserva.CONFIRMADA || r.getEstado()!= EstadoReserva.CANCELADA || r.getEstado()!= EstadoReserva.LEVANTADA) {
				return false;
			}
			return true;
		}

//--------------------------------------------------------------------
		/*
		 * Cria uma reserva vazia e adiciona ao respetivo cliente
		 * adiconarNaReserva() vai asseguir adicionar cada item a reserva
		 * 
		 */
		
		public Reserva criarReserva(int uti, int idReserva,String input) {
			Utilizador p = pesquisarUtilizador(uti);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
			LocalDateTime dt = LocalDateTime.parse(input, formatter);
			Cliente c = (Cliente) p;
			
			Reserva r = new Reserva(idReserva, dt);
			c.adicionarReserva(r);
			return r;
		}
		
		public void adicionarNaReserva(int idProd, int qtd, Reserva r) {
			Produto p = pesquisarProduto(idProd);
			r.adicionarItem(p, qtd);
		}
		
		public void cancelarReserva(int idReserva, int uti) {
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			
			c.cancelarReserva(idReserva);
		}
		
		public void apagarReserva(int idReserva,int uti){
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			
			c.apagarReserva(idReserva);
		}
		
		public boolean imprimirReservasdeUti(int uti) {
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			return c.imprimir();
		}

//--------------------------------------------------------------------
		/*
		 * Consultar Reservas Pendentes
		 * Percorre a lista de reservas e mostra apenas as que estão no estado PENDENTE.
		 */
		public boolean consultarReservasPendentes() {
			System.out.println("--- CONSULTAR RESERVAS PENDENTES ---");
			boolean encontrou = false;
			int a = 0;
			for (Utilizador u : utilizadores) {
				if(u instanceof Cliente) {
					Cliente c = (Cliente) u;
					a = c.consultar();
					if(a == 1) {
						encontrou = true;
					}
				}
			}
			if (!encontrou) {
				System.out.println("Não existem reservas pendentes de momento.");
			}
			return encontrou;
	}

//--------------------------------------------------------------------
		/*
		 * Confirmar Reserva
		 * O funcionário procura a reserva pelo ID e confirma-a.
		 * Ao confirmar, o stock dos produtos é reduzido.
		 */
		public void confirmarReserva(int id) {
			Reserva r = pesquisarReserva(id);

			if (r.getEstado() != EstadoReserva.PENDENTE) {
				System.out.println("Esta reserva não pode ser confirmada. Estado atual: " + r.getEstado());
				return;
			}

			// Confirma a reserva e reduz o stock de cada produto reservado
			r.confirmar();
		}
}
