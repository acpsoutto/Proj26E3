package proj26E3;
/**
 * Classe principal de gestão do bar.
 * Centraliza todas as operações sobre utilizadores, produtos,
 * reservas e pedidos do sistema.
 */
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GerirBar {
	private ArrayList<Utilizador> utilizadores;
	private ArrayList<Produto> produtos;
	
	public GerirBar() {
		utilizadores = new ArrayList<>();
		produtos = new ArrayList<>();
	}
	
	/**
	 * Autentica um utilizador verificando o número e a palavra-passe.
	 * @param num -número identificador do utilizador
	 * @param chave - palavra-passe a verificar
	 * @return true, caso as credenciais forem validar e falso caso contrario
	 */
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
	
	/**Pesquisa um utilizador pelo número de identificação.
	 * @param num -número do utilizador a pesquisar
	 * @return o utilizador e null se nao existir
	 */
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
	
	/**
	 * Adiciona um novo utilizador ao sistema
	 * @param num -número identificador
	 * @param nome - nome completo
	 * @param mail - endereço de e-mail
	 * @param pw - palavra-passe
	 * @param tipo -tipo de utilizador a criar
	 */
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
	
	
	/**
	 * Pesquisa um produto pelo seu identificador.
	 * @param id - identificador do produto
	 * @return o produto encontrado ou null se nao existir
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

	/**
	 * Pesquisa por um produto parcela
	 * @param id
	 * @return
	 */
	public Produto pesquisarParcela(int id) {
		if(produtos.isEmpty()){
			return null;
		}
		for(Produto p : produtos ) {
			if(!(p instanceof Elementar || p instanceof Composto)) {
				if(p.getId() == id) {
					return p;
				}
			}
		}
		return null;
	}
	
	/**
	 * Pesquisa se existem 2 ou mais parcelas no sistema
	 * @return
	 */
	public boolean pesquisarSeExiste() {
		int i = 0;
		for(Produto p : produtos ) {
			if(!(p instanceof Elementar || p instanceof Composto)) {
				i++;
			}
		}
		if(i >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Pesquisa se uma parcela já foi usada no Produto composto
	 * @param id
	 * @param parce
	 * @return
	 */
	public boolean pesquisarJaUsado(int id, int parce) {
		Produto p = pesquisarProduto(id);
		Composto c = (Composto)p;
		p = pesquisarProduto(parce);
		if(c.jaExiste(p)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Pesquisa por um produto composto
	 * @param id
	 * @return
	 */
	public boolean pesquisarComposto(int id) {
		Produto p = pesquisarProduto(id);
		if(p instanceof Composto) {
			return true;
		}
		return false;
	}
	
	/**
	 * Cria e adiciona um novo produto à lista de produtos
	 * @param id - identificador único do produto
	 * @param nome - nome do produto
	 * @param preco - preço unitário
	 * @param stock - quantidade inicial em stock
	 * @param validade - meses de validade do lote inicial
	 * @param opc - opcão do tipo de produto
	 */
	public void adicionarProduto(int id, String nome, double preco, int stock, int validade,int opc) {
		if(opc == 2) {
			Elementar p = new Elementar(id, nome, preco);
			p.adicionarStock(stock, validade);
			produtos.add(p);
			System.out.println("Produto adicionado");
		}else {
			Composto p = new Composto(id, nome, preco);
			produtos.add(p);
		}
	}
	
	/**
	 * Cria e adiciona uma Parcela a Produtos
	 */
	public void adicionarParcela(int id, String nome, int stock, int validade) {
		Produto p = new Produto(id,nome);
		p.adicionarStock(stock, validade);
		produtos.add(p);
		System.out.println("Produto adicionado");
	}
	
	/**
	 * Adiciona uma parcela a um Composto
	 * @param id
	 * @param idParce
	 * @param qtd
	 */
	public void adiconarNoComposto (int id, int idParce, double qtd) {
		Produto p = pesquisarProduto(id);
		Composto c = (Composto) p;
		p = pesquisarProduto(idParce);
		c.adicionarNoComposto(p , qtd);	
	}

	/**
	 * Imprime todos os produtos registados
	 */
	public void imprimirProdutos() {
		for(Produto p : produtos) {
			System.out.println(p);
		}
	}
	
	/**
	 * Imprime os conteudos de um composto e o máximo de produtos a ser adicionados a stock
	 * @param id
	 * @return
	 */
	public int imprimirConteudos(int id) {
		System.out.println("Produtos que utiliza:");
		Produto p = pesquisarProduto(id);
		Composto c = (Composto) p;
		int i = c.imprimirParcelas();	
		System.out.println("\n Podem ser adicionadas um maximo de "+i+" do produto.");
		return i;
	}

	/**
	 * Imprime o ID, nome e preço de cada produto
	 */
	public void imprimirPreços() {
		for(Produto p : produtos){
			if(p instanceof Elementar || p instanceof Composto) {
				System.out.println("Produto: " +p.getId()+ " | "+p.getNome()+" Preço="+p.getPreco());
			}
		}
		
	}

	/**
	 * Atualiza o preço de um produto existente.
	 * @param id -identificador do produto
	 * @param preco -novo preço a aplicar
	 */
	public void atualizarPreco(int id, double preco) {
		Produto p = pesquisarProduto(id);
		p.setPreco(preco);
	}
	
	/**
	 * Adiciona um novo lote de stock a um produto parcela ou elementar
	 * @param id -identificador do produto
	 * @param quant -quantidade a adicionar
	 * @param val -meses de validade do novo lote
	 */
	public void adicionarStock(int id, int quant, int val) {
		Produto p = pesquisarProduto(id);
		p.adicionarStock(quant, val);
	}
	/**
	 * Adicionar stock a um produto composto
	 * @param id
	 * @param quant
	 */
	public void adicionarStock(int id, int quant) {
		Produto p = pesquisarProduto(id);
		Composto c = (Composto)p;
		c.produzir(quant);
	}
	
	/**
	 * Verifica se um produto tem stock suficiente para a quantidade pedida.
	 * @param id identificador do produto
	 * @param qtd quantidade desejada
	 * @return true se o stock for suficiente e false caso contrario
	 */
	public boolean verificarStock(int id, int qtd) {
		Produto p = pesquisarProduto(id);
		if(p.getStock() < qtd) {
			return false;
		}
		return true;
	}
	
	/**
	 * Reduz o stock de um produto após uma venda.
	 * @param id identificador do produto
	 * @param quant quantidade a subtrair do stock
	 */
	public void reduzirStock(int id, int quant) {
		Produto p = pesquisarProduto(id);
		p.consumirQuantidade(quant);
	}
	
	/**
	 * Verifica a quantidade de parcelas de um composto
	 * @param id
	 * @return
	 */
	public boolean verficarQuantidadeParce(int id){
		Produto p = pesquisarProduto(id);
		Composto c = (Composto) p;
		return c.getSize();
	}


	/**
	 * Lista todos os produtos com stock disponível
	 * @return true, se existirem produtos disponiveis e false caso contrario
	 */
	public boolean consultarProdutosDisponiveis() {
		System.out.println("\n----- PRODUTOS DISPONIVEIS -----");
		boolean encontrou = false;
		for (Produto p: produtos) {
			if (p.getStock()!=0 && (p instanceof Composto || p instanceof Elementar)) {
				
				System.out.println("|ID: " +p.getId() + "\n|Nome: " + p.getNome() + "\n|Preço: " + p.getPreco() + "€");
			    encontrou =true;
			}
		}
		if (!encontrou) {
			System.out.println("Não existem produtos disponiveis no stock este momento!");
		}
		return encontrou;
	}
	
		/**
		 * Cria um novo pedido e associa-o ao funcionário do bar indicado.
		 * @param num Unúmero do utilizador (funcionário do bar)
		 * @param idPedido identificador do novo pedido
		 * @return o pedido criado
		 */
		public Pedido registrarPedido(int numU, int idPedido) {
			Utilizador u = pesquisarUtilizador(numU);
			FuncionarioBar f = (FuncionarioBar) u;
			Pedido pd = new Pedido (idPedido, LocalDateTime.now());
			f.adicionarPedido(pd);
			return pd;
		}

		/**
		 * Adiciona um item (produto e quantidade) a um pedido existente.
		 * @param idP - identificador do produto
		 * @param qtd- quantidade do produto
		 * @param pd- pedido ao qual o item sera adicionado
		 */
		public void adicionarNoPedido(int idP, int qtd, Pedido pd) {
			Produto p = pesquisarProduto(idP);
			pd.adicionarItem(p, qtd);
		}
		
		/**
		 * Remove um pedido da lista do funcionário do bar indicado.
		 * @param idPedido identificador do pedido
		 * @param uti - numero do utilizador (funcionario bar)
		 */
		public void apagarPedido(int idPedido, int uti) {
			Utilizador u = pesquisarUtilizador(uti);
			FuncionarioBar fb = (FuncionarioBar) u;
			fb.apagarPedido(idPedido);
		}
		
		/**
		 * Pesquisa uma reserva pelo seu ID, percorrendo todos os clientes registados.
		 * @param id - identificador de reserva
		 * @return a reserva encontrada e null se nao existir
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
		
		/**
		 *Verifica se o estado de uma reserva impede alterações,ou seja, se já está
		 * (CONFIRMADA),(CANCELADA) ou (LEVANTADA).
		 * @param id  identificador da reserva
		 * @return true se o estado bloquear edicao e false caso contrario
		 */
		public boolean detetarEstado(int id) {
			Reserva r = pesquisarReserva(id);
			if(r.getEstado()!= EstadoReserva.CONFIRMADA || r.getEstado()!= EstadoReserva.CANCELADA || r.getEstado()!= EstadoReserva.LEVANTADA) {
				return false;
			}
			return true;
		}
		
		/**
		 * Cria uma nova reserva no estado (PENDENTE) e associa ao cliente indicado.
		 * @param uti -número do utilizador (cliente)
		 * @param idReserva -identificador da nova reserva
		 * @param input -data e hora no formato {@code "yyyy-M-d H:m"}
		 * @return RESERVA CRIADA
		 */
		public Reserva criarReserva(int uti, int idReserva,LocalDateTime dt) {
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			
			Reserva r = new Reserva(idReserva, dt);
			c.adicionarReserva(r);
			return r;
		}
		
		/**
		 * Adiciona um item (produto e quantidade) a uma reserva existente.
		 * @param idProd - identificador do produto
		 * @param qtd -quantidade do produto
		 * @param r - reserva à qual o item será adicionado
		 */
		public void adicionarNaReserva(int idProd, int qtd, Reserva r) {
			Produto p = pesquisarProduto(idProd);
			r.adicionarItem(p, qtd);
		}
		
		/**
		 * Cancela a reserva com o ID indicado para o cliente especificado.
		 * @param idReserva -identificador da reserva a cancelar
		 * @param uti - número do utilizador (cliente)
		 */
		public void cancelarReserva(int idReserva, int uti) {
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			
			c.cancelarReserva(idReserva);
		}
		
		/**
		 * Apaga a reserva com o ID indicado para o cliente especificado.
		 * @param idReserva -identificador da reserva a apagar
		 * @param uti - número do utilizador (cliente)
		 */
		public void apagarReserva(int idReserva,int uti){
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			c.apagarReserva(idReserva);
		}
		
		/**
		 * Imprime todas as reservas do cliente indicado.
		 * @param uti - numero do utilizador (cliente)
		 * @return true, caso exista reservas e false se a lista estiver vazia
		 */
		public boolean imprimirReservasdeUti(int uti) {
			Utilizador p = pesquisarUtilizador(uti);
			Cliente c = (Cliente) p;
			return c.imprimir();
		}

		/**
         *Consulta e imprime todas as reservas no estado (PENDENTE)
         * de todos os clientes registados.
		 * @return true, se existir reservas pendentes e false, caso contrario
		 */
		public boolean consultarReservasPendentes() {
			System.out.println("--- CONSULTAR RESERVAS PENDENTES ---");
			boolean encontrou = false;
			for (Utilizador u : utilizadores) {
				if(u instanceof Cliente) {
					Cliente c = (Cliente) u;
					if(c.consultar()) {
						encontrou = true;
					}
				}
			}
			if (!encontrou) {
				System.out.println("Não existem reservas pendentes de momento.");
			}
			return encontrou;
	    }

		/**
		*Confirma a reserva com o IDreserva indicado, desde que esteja no estado
         *(PENDENTE). Caso contrário, informa o estado atual.
		 * @param id - identificador da reserva
		 */
		public void confirmarReserva(int idReserva) {
			Reserva r = pesquisarReserva(idReserva);

			if (r.getEstado() != EstadoReserva.PENDENTE) {
				System.out.println("Esta reserva não pode ser confirmada. Estado atual: " + r.getEstado());
				return;
			}
			r.confirmar();
		}
		/**
		 * Verifica se um produto ja foi utilizado num pedido
		 * @param id
		 * @param idPedido
		 * @return
		 */
		public boolean verificarJaExiste(int id,int idPedido) {
			for(Utilizador u : utilizadores) {
				if(u instanceof FuncionarioBar) {
					FuncionarioBar fb = (FuncionarioBar)u ;
					if(fb.vericarJaExiste(id,idPedido)) {
						return true;
					}
				}
				if(u instanceof Cliente) {
					Cliente c = (Cliente) u;
					if(c.vericarJaExiste(id,idPedido)) {
						return true;
					}
				}
			}
			return false;
		}
		/**
		 * Pesquisa utilizadores por email
		 * @param mail
		 * @return
		 */
		public String pequisarEmail(String mail) {
			for(Utilizador u:utilizadores) {
				if(u.getEmail().equals(mail)) {
					return mail;
				}
			}
			return null;
		}
		
		
		/**
		 * Calcula o valor total dos pedidos (balcao e reservas).
		 */
		public void totalVendas() {
			double total = 0;
			for (Utilizador f: utilizadores) {
				if (f instanceof FuncionarioBar) {
					FuncionarioBar fb = (FuncionarioBar) f;
					total += fb.funcionarioTotalPedido();
				}
				if (f instanceof Cliente) {
					Cliente c = (Cliente) f;
					total += c.clienteTotalReserva();
				}
			}
			System.out.println(total);
		}
		
		/**
		 * Calcula o número total de  pedidos (balcao e reservas).
		 */
		public void numeroVendas() {
			double total = 0;
			for (Utilizador f: utilizadores) {
				if (f instanceof FuncionarioBar) {
					FuncionarioBar fb = (FuncionarioBar) f;
					total += fb.numeroPedidos();
				}
				if (f instanceof Cliente) {
					Cliente c = (Cliente) f;
					total += c.numeroPedidos();
				}
			}
			System.out.println(total);
		}
		
		/**
		 * Imprime o relatorio de vendas 
		 */
		public void imprimirRelatorio()	{
		    System.out.println("\n================ RELATÓRIO DE VENDAS ================");

		    System.out.printf("%-15s %-15s %-15s%n","Data","Qtd Produtos","Total (€)");

		    System.out.println("-----------------------------------------------------");

		    int numeroVendas = 0;
		    double montanteTotal = 0;

		    // Pedidos dos funcionários
		    for(Utilizador u : utilizadores) {

		        if(u.getTipo() == TipoUtilizador.FUNCIONARIO_BAR|| u.getTipo() == TipoUtilizador.CLIENTE) {
		        	if (u instanceof FuncionarioBar) {
		        		FuncionarioBar fb = (FuncionarioBar) u;
		        		
			            for(Pedido p : fb.getPedidos()) {
			            	
			            	String diahora = p.getDataHora().getDayOfMonth() + "/" + p.getDataHora().getMonth() + "|" + p.getDataHora().getHour() + ":" + p.getDataHora().getMinute();
			                System.out.printf("%-15s %-15d %-15.2f%n", diahora, p.getQuantidadeProdutos(), p.getTotal());

			                numeroVendas++;
			                montanteTotal += p.getTotal();
			            }
		        	}
		        	if (u instanceof Cliente) {
		        		Cliente c = (Cliente) u;
		        		
		        		for (Reserva r : c.getReservas()) {
		        			if (r.getEstado()==EstadoReserva.LEVANTADA || r.getEstado()==EstadoReserva.NAO_LEVANTADA) {
		        				String diahora = r.getDataHora().getDayOfMonth() + "/" + r.getDataHora().getMonth() + "|" + r.getDataHora().getHour() + ":" + r.getDataHora().getMinute();
		        				System.out.printf("%-15s %-15d %-15.2f%n", diahora, r.getQuantidadeProdutos(), r.getTotal());

				                numeroVendas++;
				                montanteTotal += r.getTotal();	
		        			}
		        		}
		        	}
		        }
		    }
		    System.out.println("-----------------------------------------------------");
		    System.out.println("Número de vendas: " + numeroVendas);
		    System.out.printf("Montante total: %.2f €%n", montanteTotal);
		}

		/**
		 * Pesquisa um pedido pelo seu id
		 * @param idPedido - id do pedido a pesquisar
		 * @return
		 */
		public Pedido pesquisarPedido(int idPedido) {
			for(Utilizador u : utilizadores) {
				if(u instanceof FuncionarioBar) {
					FuncionarioBar fb = (FuncionarioBar)u;
					Pedido P = fb.pesquisarPedido(idPedido);
					if(P != null) {
						return P;
					}
				}
				if(u instanceof Cliente) {
					Cliente c = (Cliente)u;
					Pedido P = c.pesquisarpedido(idPedido);
					if(P != null) {
						return P;
					}
				}
			}
			return null;
		}
		/**
		 * Imprime reservas confirmadas
		 */
		public void imprimirReservasConfirmadas() {
			for(Utilizador u : utilizadores) {
				if(u instanceof Cliente) {
					Cliente c = (Cliente)u ;
					c.imprimirRervasConfirmadas();
				}
			}
			
		}
		/**
		 * Verifica se uma reserva esta no estado confirmada
		 * @param idReserva
		 * @return
		 */
		public boolean reservaValida(int idReserva) {
			for(Utilizador u : utilizadores) {
				if(u instanceof Cliente) {
					Cliente c = (Cliente)u ;
					if(c.verificarReserva(idReserva)){
						return true;
					}
				}
			}
			return false;
		}
		/**
		 * Pesquisa o cliente por uma reserva
		 * @param idReserva
		 * @return
		 */
		public Cliente pesquisarClientePorReserva(int idReserva) {
			for (Utilizador u : utilizadores) {
				if (u instanceof Cliente) {
					Cliente c = (Cliente) u;
					if (c.encontrarReserva(idReserva) != null) {
						return c;
					}
				}
			}
			return null;
		}
		/**
		 * Remove um produto da lista de produtos
		 * @param id
		 */
		public void removerProduto(int id) {
			produtos.removeLast();
		}
}

