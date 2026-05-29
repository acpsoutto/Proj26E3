package proj26E3;

/**
 * Classe principal do programa, responsável pela interação com o utilizador
 ** através de menus em modo de texto (consola).
 **/
import java.util.*;
//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

public class Teste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GerirBar gb = new GerirBar();
		CategoriaProduto categoria = null;
		int idReserva = 0;
		int idPedido = 0;
		int opc;
		TipoUtilizador tipoU = TipoUtilizador.ADMNISTRACAO;
		
		gb.adicionarUtilizador(1, "Admin", "admin@gmail.com", "123", tipoU);
		
		do {
			
				System.out.println("========== MENU USÚARIO ==========");
				System.out.println("Seleciona perfil");
				System.out.println("1- Administrador");
				System.out.println("2- Gerente");
				System.out.println("3- Funcionario do Bar");
				System.out.println("4- Cliente");
				System.out.println("0- Terminar Programa");
				System.out.println("===================================");
				System.out.print("\nOpção:");
				opc = inserir(sc);
				System.out.println();
				
			
				if (opc != 0 && opc != 1 && opc != 2 && opc != 3 && opc != 4 ) {
					System.out.println("Esse perfil não existe! Tente novamente.");
					continue;
				}
				
				if(opc == 0) {
					System.out.println("Obrigado por utilizar o programa.");
					break;
				}
				
				int intentos=0;
				Utilizador f=null;
				int uti =0;
				do {
					System.out.print("Insira o Número de Identificação:");
					uti = inserir(sc);;
					System.out.println();
					System.out.print("Insira a palavra-chave:");
					String chave = sc.nextLine();
					System.out.println();
					/**
			            * Lê o número de identificação e a palavra-chave do utilizador.
			            * Valida se o utilizador existe, se a palavra-chave está correta
			            * e se o perfil selecionado corresponde ao tipo do utilizador.
			            */
					
					f = gb.pesquisarUtilizador(uti);
					
						
					if (f == null) {
						System.out.println("Utilizador ou palavra-chave errada. Tente outra vez.\n");
						intentos++;
						continue;
						}
					if(!f.getPw().equals(chave)){
						System.out.println("Chave errada");
						intentos++;
						continue;	
					}
					break;
				}while(intentos<5);	
				
				if(intentos>=5) {
					System.out.println("Limite de intentos atingido");
					continue;
				}
						
				// Validação do tipo de perfil correspondente
					
				if (opc == 1 && f.getTipo()!= TipoUtilizador.ADMNISTRACAO) { 
					System.out.println("Este utilizador não é Administrador!\n");
					continue;
				}
				if (opc == 2 && f.getTipo()!= TipoUtilizador.GERENTE) {
					System.out.println("Este utilizador não é Gerente!\n");
					continue;
				}
				if (opc == 3 && f.getTipo()!= TipoUtilizador.FUNCIONARIO_BAR) {
					System.out.println("Este utilizador não é do Bar!\n");
					continue;
				}
				if (opc == 4 && f.getTipo()!= TipoUtilizador.CLIENTE) {
					System.out.println("Este utilizador não é cliente");
					continue;
				}
						
				
	
				
				int escolha = -1; // Inicializado para evitar erros de compilação
				
				// --- Menu Administrador ---
	            /**
	             * Permite ao administrador adicionar novos utilizadores ao sistema:
	             * funcionários do bar, clientes, gerentes e outros administradores.
	             * Verifica se o ID introduzido já está em uso antes de criar o utilizador.
	             */
				if(opc == 1){
					do {
						System.out.println("1- Adicionar Funcionário do bar");
						System.out.println("2- Adicionar Cliente");
						System.out.println("3- Adicionar Gerente");
						System.out.println("4- Adicionar Admnistrador");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção:");
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
						case 1: 
							System.out.println("ID do Funcionário:");
							int id = inserir(sc);
							
							if (gb.pesquisarUtilizador(id)!= null) {
								System.out.println("ID já existe.");
								continue;
							}
							System.out.println("Email do Funcionário:");
							String mail = inserirEmail(sc,gb);
							
							System.out.println("PassWord do Funcionário:");
							String pw = sc.next();
							
							System.out.println("Nome do Funcionário:");
							String nome = sc.next();
							
							TipoUtilizador tipo = TipoUtilizador.FUNCIONARIO_BAR;
							
							gb.adicionarUtilizador(id, nome, mail, pw, tipo);
							System.out.println("Funcionário adicionado");
							break;
							
						case 2:
							System.out.println("ID do Cliente:");
							id = inserir(sc);
							
							if (gb.pesquisarUtilizador(id)!= null) {
								System.out.println("ID já existe.");
								continue;
							}
							System.out.println("Email do Cliente:");
							mail = inserirEmail(sc,gb);
							
							System.out.println("PassWord do Cliente:");
							pw = sc.next();
							
							System.out.println("Nome do Cliente:");
							nome = sc.next();
							
							tipo = TipoUtilizador.CLIENTE;
							
							gb.adicionarUtilizador(id, nome, mail, pw, tipo);
							System.out.println("Cliente adicionado");
							break;
							
						case 3: 
							System.out.println("ID do Gerente:");
							id = inserir(sc);
							
							if (gb.pesquisarUtilizador(id)!= null) {
								System.out.println("ID já existe.");
								continue;
							}
							System.out.println("Email do Gerente:");
							mail = inserirEmail(sc,gb);
							
							System.out.println("PassWord do Gerente:");
							pw = sc.next();
							
							System.out.println("Nome do Gerente:");
							nome = sc.next();
							
							tipo = TipoUtilizador.GERENTE;
							
							gb.adicionarUtilizador(id, nome, mail, pw, tipo);
							System.out.println("Gerente adicionado");
							break;
						case 4: 
							System.out.println("ID do Admnistrador:");
							id = inserir(sc);
							
							if (gb.pesquisarUtilizador(id)!= null) {
								System.out.println("ID já existe.");
								continue;
							}
							System.out.println("Email do Admnistrador:");
							mail = inserirEmail(sc,gb);
							
							System.out.println("PassWord do Admnistrador:");
							pw = sc.next();
							
							System.out.println("Nome do Admnistrador:");
							nome = sc.next();
							
							tipo = TipoUtilizador.ADMNISTRACAO;
							
							gb.adicionarUtilizador(id, nome, mail, pw, tipo);
							System.out.println("Admnistrador adicionado");
							break;
							
						case 10:
							System.out.println("A sair para o login");
							break;
							
						case 0:
							System.out.println("Obrigado por utilizar o programa.");
							opc = 0;
							break;
							
						default :
							System.out.println("Opção Invalida! Tente outra vez");
						}
					} while (escolha != 10 && opc != 0);
				}
				 
					
				
				/* 
				 * USUARIO - GERENTE
				 */
				if(opc == 2){
					do {
						System.out.println("1- Adicionar Produtos");
						System.out.println("2- Consultar Produtos");
						System.out.println("3- Consultar Preços"); //Menu do Gerente
						System.out.println("4- Atualizar Preços");
						System.out.println("5- Adicionar stock");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção:");
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
						/** Adiciona um novo produto ao sistema.
				         * Solicita ID, nome, preço, categoria, stock e validade em meses.
				         * Valida se o ID já existe e se os valores de stock e validade são positivos.
				         */
						case 1:
							System.out.println("ID do novo produto");
							int id = inserir(sc);
							if(id == 0) {
								System.out.println("ID indisponivel! Tente novamente.");
								break;
							}
							if(gb.pesquisarProduto(id) != null) {
								System.out.println("ID já em utilização! Tente novamente.");
								break;
							}
							System.out.println("Nome do novo produto");
							String nome = sc.nextLine();
							
							System.out.println("Preço do produto");
							double preco = sc.nextDouble();
							sc.nextLine();
							
							System.out.println("Qual a categoria do produto (ELEMENTAR|COMPOSTO|BEBIDA)");
							String input = sc.nextLine().toUpperCase().trim();
							try {
					            categoria = CategoriaProduto.valueOf(input);
					        } catch (IllegalArgumentException e) {
					            System.out.println("Categoria inválida. Tente novamente.");
					            break;
					        } 
							System.out.println("Qual é a quantidade a armazenar em stock produto");
							int stock = inserir(sc);
							if(stock<0) {
								System.out.println("Numero de Stock invalido! Tente novamente");
								break;
							}
							System.out.println("Quantos meses de validade tem o produto");
							int validade = inserir(sc);
							if(validade<0) {
								System.out.println("Validade invalida! Tente novamente");
								break;
							}
							gb.adicionarProduto(id,nome,preco,categoria,stock, validade);
							break;
						/** Imprime todos os produtos registados no sistema. */	
						case 2:
							gb.imprimirProdutos();
							break;
						/** Imprime o ID, nome e preço de cada produto. */	
						case 3:
							gb.imprimirPreços();
							break;
						/** Atualiza o preço de um produto existente.
					      * Solicita o ID do produto e o novo preço.
					      * Verifica se o produto existe antes de atualizar.
					      */	
						case 4:
							System.out.print("Qual o id do produto a nome: ");
							id = inserir(sc);
							System.out.println();
							if(gb.pesquisarProduto(id) !=null) {
								System.out.print("Novo preço do produto: ");
								double novoPreco = sc.nextDouble();
								sc.nextLine();
								System.out.println();
								
								gb.atualizarPreco(id, novoPreco);
							} else{
								System.out.println("Esse id não esta atribuido a nenhum produto! Tente outra vez!");
							}
							break;
						/** Adiciona stock a um produto existente.
					     * Solicita o ID do produto, a quantidade a adicionar e a validade em meses do novo lote.
					     * Verifica se o produto existe antes de adicionar.
					     */	
						case 5:
							System.out.print("Qual o id do produto: ");
							id = inserir(sc);
							System.out.println();
							if(gb.pesquisarProduto(id) !=null) {
								System.out.println("Quantidade a adicionar em stock:");
								int quant = inserir(sc);
								System.out.println("Validade em meses do novo lot:");
								int val = inserir(sc);
								
								gb.adicionarStock(id, quant, val);
							}else {
								System.out.println("Esse id não esta atribuido a nenhum produto! Tente outra vez!");
							}
							break;
						/** Regressa ao menu de login. */
						case 10:
							System.out.println("A sair para o login");
							continue;
						/** Encerra o programa. */	
						case 0:
							System.out.println("Obrigado por utilizar o programa.");
							opc = 0;
							break;
							
						default :
							System.out.println("Opção Invalida! Tente outra vez");
						}
					}while(opc != 0 && escolha != 10);
				}
				/*
				 * USUARIO - FUNCIONARIO DO BAR
				 */
				if(opc == 3){
					do {
						System.out.println("===== MENU FUNCIONARIO BAR =====");
						System.out.println("1- Consultar Produtos Disponíveis");
						System.out.println("2- Registrar pedido");
						System.out.println("3- Consultar reservas pendentes");
						System.out.println("4- Confirmar reserva");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção:");
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
						/** Consulta e imprime todos os produtos com stock disponível. */
						case 1:
							gb.consultarProdutosDisponiveis(); 
							break;
						/** Regista um novo pedido para o funcionário autenticado.
					     * Solicita produtos e quantidades em ciclo até o utilizador inserir 0.
					     * Valida stock disponível para cada produto adicionado.
					     * Se nenhum item for adicionado, o pedido é descartado.
					      */
						case 2:
							idPedido += 1;
							Pedido pd = gb.registrarPedido(uti, idPedido);
							int idP;
							boolean tenta = false;
							
							System.out.println("--- REGISTAR NOVO PEDIDO ---");
							
							do {
								
							
								System.out.print("Introduza o ID do produto (Insira 0 para parar de adicionar): ");
								idP = inserir(sc);
								
								Produto p = gb.pesquisarProduto(idP);
								
								if (idP == 0) {
									break;
								}
								
								if (p == null) {
									System.out.println("Produto não encontrado!");
									continue;
								}
								
								if (p.getStock()==0 ) {
									System.out.println("Erro: Produto sem stock disponível!");
									continue;
								}
								
								System.out.print("Introduza a quantidade desejada: ");
								int qtd = inserir(sc);
								
								if(qtd<=0) {
									System.out.println("Erro: A quantidade deve ser maior que 0.");
									continue;
								}
								
								int stockAtual = p.getStock();
								
								if (qtd > stockAtual) {
									System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
									continue;
								}
								
								gb.adicionarNoPedido(idP, qtd, pd);
								tenta = true;
								
							} while (idP!=0);
							
							if(!tenta) {
								gb.apagarPedido(idPedido, uti);
								idPedido -=1;
							} else {
								System.out.println("O pedido foi registrado!");
							}
							break;
						/** Consulta e imprime todas as reservas no estado PENDENTE. */
						case 3:
							gb.consultarReservasPendentes();
							break;
						/** Confirma uma reserva pendente pelo seu ID.
					     * Verifica se existem reservas pendentes antes de solicitar o ID.
					     * Valida se a reserva existe antes de confirmar.
					     */
						case 4:
							if(!gb.consultarReservasPendentes()){
								break;
							}
							System.out.println("================================");
							System.out.println();
							System.out.println("Introduza o id da reserva que quer confirma:");
							int id = inserir(sc);
							if(gb.pesquisarReserva(id)== null) {
								System.out.println("Não existe reserva com esse id:");
								break;
							}
							gb.confirmarReserva(id);
						/** Regressa ao menu de login. */
						case 10:
							System.out.println("A sair para o login");
							break;
						/** Encerra o programa. */
						case 0:
							System.out.println("Obrigado por utilizar o programa.");
							opc = 0;
							break;
						default:
							System.out.println("Opção Inválida! Tente outra vez");
						}
					}while(escolha != 0 && escolha != 10);
				}
				/*
				 * USUARIO - CLIENTE
				 */
				if(opc == 4){
					do {
						System.out.println("1- Fazer Reserva");
						System.out.println("2- Consultar Reservas");
						System.out.println("3- Canselar Reserva");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção:");	
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
						/** Cria uma nova reserva para o cliente autenticado.
				         * Verifica se existem produtos disponíveis antes de iniciar.
				         * Solicita a data/hora de recolha e os produtos a reservar em ciclo.
				         * Valida stock disponível para cada produto adicionado.
				         * Se nenhum item for adicionado, a reserva é descartada.
				         */
						case 1:
							boolean tenta = false;
							int id;
							if(!gb.consultarProdutosDisponiveis()) {
								break;
							}
							System.out.println("--- FAZER PRÉ-RESERVA ---");
							System.out.println("Quando quer recolher (Introduza na forma de ano-mes-dia Hora:min):");
							String input = sc.nextLine();
							idReserva += 1;
							Reserva r = gb.criarReserva(uti,idReserva, input);
							do {
								System.out.println("Id do produto a utilizar (insira '0' para parar de adicionar):");
								id = inserir(sc);
								
								if(id == 0) {
									break;
								}
								if(gb.pesquisarProduto(id) == null) {
									System.out.println("Produto não encontrado! Tente Novamente");
									continue;
								}
								System.out.println("Quantidade:");
								int qtd = inserir(sc);
								if(qtd <= 0) {
									System.out.println("Erro! Quantidade tem de ser superiror a 0.");
									System.out.println("Tente outra vez.");
									continue;
								}else if(!gb.verificarStock(id, qtd)){
									System.out.println("Quantidade pedida acima do stock!");
									System.out.println("Tente outra vez.");
									continue;
								}
								gb.adicionarNaReserva(id,qtd, r);
								tenta = true;
							}while(id != 0);
							if(!tenta) {
								gb.apagarReserva(idReserva,uti);
								idReserva -=1;
							}
						break;
						/** Imprime todas as reservas do cliente autenticado. */
						case 2:
							if(!gb.imprimirReservasdeUti(uti)){
								System.out.println("Não existem Reservas!");
							}
						break;
						/** Cancela uma reserva do cliente autenticado pelo seu ID.
				         * Imprime as reservas existentes antes de solicitar o ID.
				         * Verifica se a reserva existe e se o seu estado permite cancelamento.
				         */
						case 3:
							if(!gb.imprimirReservasdeUti(uti)){
								System.out.println("Não existem Reservas!");
								break;
							}
							System.out.println("================================");
							System.out.println();
							System.out.println("Qual reserva quer cancelar:");
							int reservaid = inserir(sc);
							if(gb.pesquisarReserva(reservaid)== null) {
								System.out.println("Reserva não existe");
								break;
							}
							if(gb.detetarEstado(reservaid)) {
								System.out.println("A reserva que selecionou já não pode ser cancelada, uma vez que se encontra num estado avançado");						
							}else {
								gb.cancelarReserva(reservaid, uti);
								System.out.println("Reserva cancelada");
							}
							
							
							break;
						/** Regressa ao menu de login. */
						case 10:
							System.out.println("A sair para o login");
							break;
						/** Encerra o programa. */	
						case 0:
							System.out.println("Obrigado por utilizar o programa.");
							opc = 0;
							break;
							
						default :
							System.out.println("Opção Invalida! Tente outra vez");
						
						}
					}while(escolha != 0 && escolha != 10);
				}
			
			}while(opc != 0);
			sc.close();
		
	}
	public static int inserir(Scanner sc) {
		while(true) {
			try {
				int a=sc.nextInt();sc.nextLine();
				return a;
			}catch(InputMismatchException e) {
				System.out.println("So e valido numeros, insira de novo:\n");
				sc.nextLine();
				continue;
			}
		}
	}
	public static String inserirEmail(Scanner sc,GerirBar gb) {
		String patron = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		String mail=null;
		while(mail==null) {
		
			String a=sc.nextLine().trim();
			if(a.matches(patron)) {
				mail=a;
			}else {
				System.out.println("Email invalido, inserir no siguiente formato: usuario@gmail.com\nInsira de novo:\n");
				continue;
			}
			if(gb.pequisarEmail(mail)!=null) {
				System.out.println("Email ya en uso\nIngrese de novo:\n");
				mail=null;
				continue;
			}
		}
		
		return mail;
	}
	
}
