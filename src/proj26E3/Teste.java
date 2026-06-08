package proj26E3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
/**
 * Classe principal do programa, responsável pela interação com o utilizador
 ** através de menus em modo de texto (consola).
 **/
import java.util.*;

public class Teste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GerirBar gb = new GerirBar();
		int idPedido = 0;
		int opc;
		
		// Utilizadores e produto de teste pré-carregados no sistema
		TipoUtilizador tipoU = TipoUtilizador.ADMNISTRACAO;
		gb.adicionarUtilizador(1, "Admin", "admin@gmail.com", "123", tipoU);
		tipoU = TipoUtilizador.GERENTE;
		gb.adicionarUtilizador(2, "Gere", "gere@gmail.com", "123", tipoU);
		tipoU = TipoUtilizador.CLIENTE;
		gb.adicionarUtilizador(4, "CLIENTE", "Cliente@gmail.com", "123", tipoU);
		tipoU = TipoUtilizador.FUNCIONARIO_BAR;
		gb.adicionarUtilizador(3, "fUNC", "FUNC@gmail.com", "123", tipoU);
		gb.adicionarProduto(1, "Maça", 1.99 , 10, 1, 2);
		gb.adicionarParcela(2,"Pão",5, 0);
        gb.adicionarStock(2, 10, 4);
        gb.adicionarParcela(3,"Queijo",16, 3);
        String insert = "2026-06-07 12:00";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
		LocalDateTime tempoP = LocalDateTime.parse(insert, formatter1);
        Reserva h = gb.criarReserva(4,idPedido, tempoP);
        gb.adicionarNaReserva(1,1,h);
        
		do {
			
				System.out.println("========== MENU UTILIZADOR ==========");
				System.out.println("Selecione o perfil pretendido:");
				System.out.println("1- Administrador");
				System.out.println("2- Gerente");
				System.out.println("3- Funcionario do Bar");
				System.out.println("4- Cliente");
				System.out.println("0- Terminar Programa");
				System.out.println("===================================");
				System.out.print("Opção:");
				opc = inserir(sc);
				System.out.println();
				
			
				if (opc != 0 && opc != 1 && opc != 2 && opc != 3 && opc != 4 ) {
					System.out.println("Esse perfil não existe! Tente novamente.");
					continue;
				}
				
				if(opc == 0) {
					System.out.println("Obrigado por utilizar o programa. Até breve!");
					break;
				}
				
				// --- Autenticação do Utilizador ---

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
						System.out.println("Palavra-chave errada. Tente outra vez.\n");
						intentos++;
						continue;	
					}
					break;
				}while(intentos<5);	
				
				if(intentos>=5) {
					System.out.println("Limite de tentativas atingido");
					continue;
				}
						
				// Validação do tipo de perfil correspondente
					
				if (opc == 1 && f.getTipo()!= TipoUtilizador.ADMNISTRACAO) { 
					System.out.println("Este utilizador não tem perfil de Administrador!\n");
					continue;
				}
				if (opc == 2 && f.getTipo()!= TipoUtilizador.GERENTE) {
					System.out.println("Este utilizador não tem perfil de Gerente!\n");
					continue;
				}
				if (opc == 3 && f.getTipo()!= TipoUtilizador.FUNCIONARIO_BAR) {
					System.out.println("Este utilizador não tem perfil de Funcionário do Bar!\n");
					continue;
				}
				if (opc == 4 && f.getTipo()!= TipoUtilizador.CLIENTE) {
					System.out.println("Este utilizador não tem perfil de Cliente!\n");
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
						System.out.println("==========================================");
						System.out.println("          MENU - ADMINISTRADOR           ");
						System.out.println("==========================================");
						System.out.println("1- Adicionar utilizador");
						System.out.println("------------------------------------------");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção: ");
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
							/**
					 		* Permite ao administrador registar um novo utilizador no sistema.
					 		* Solicita o tipo, ID, email, palavra-chave e nome.
					 		* Valida se o ID já está em uso antes de criar.
					 		*/
						case 1:
							System.out.println("------ TIPO DE UTILIZADOR ------");
							System.out.println("1- Adicionar Funcionário do bar");
							System.out.println("2- Adicionar Cliente");
							System.out.println("3- Adicionar Gerente");
							System.out.println("4- Adicionar Admnistrador");
							System.out.println("--------------------------------");
							int utilizadore = sc.nextInt();
							if(utilizadore <= 0 || utilizadore >= 5  ) {
								System.out.println("Opção invalida!");
								break;
							}
							TipoUtilizador tipo = null;
							
							System.out.println("ID do novo Utilizador: ");
							int id = inserir(sc);
							
							if (gb.pesquisarUtilizador(id)!= null) {
								System.out.println("Esse ID já está em uso. Tente outro ID.");
								continue;
							}
							System.out.println("Email do Utilizador:");
							String mail = inserirEmail(sc,gb);
							
							System.out.println("Palavra-chave do Utilizador:");
							String pw = sc.next();
							
							System.out.println("Nome do Utilizador:");
							String nome = sc.next();
							switch(utilizadore) {
							case 1 :
								tipo = TipoUtilizador.FUNCIONARIO_BAR;
								break;
							case 2 :
								tipo = TipoUtilizador.CLIENTE;
								break;
							case 3:
								tipo = TipoUtilizador.GERENTE;
								break;
							case 4:
								tipo = TipoUtilizador.ADMNISTRACAO;
								break;
							}
							
							gb.adicionarUtilizador(id, nome, mail, pw, tipo);
							System.out.println("Utilizador adicionado com sucesso!");
							break;
							
						case 10:
							System.out.println("A regressar ao menu de login");
							break;
							
						case 0:
							System.out.println("Obrigado por utilizar o Sistema.");
							opc = 0;
							break;
							
						default :
							System.out.println("Opção Invalida! Tente outra vez");
						}
					} while (escolha != 10 && opc != 0);
				}
				 
					
				// ==========================================
				// MENU GERENTE
				// ==========================================
				if(opc == 2){
					do {
						System.out.println("==========================================");
						System.out.println("             MENU - GERENTE              ");
						System.out.println("==========================================");
						System.out.println("1- Adicionar Produtos");
						System.out.println("2- Consultar Produtos");
						System.out.println("3- Consultar Preços");
						System.out.println("4- Atualizar Preços");
						System.out.println("5- Adicionar stock");
						System.out.println("6- Consultar Relatório de vendas");
						System.out.println("------------------------------------------");
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
							double preco;
							int stock=0;
							int validade=0;
							int idParce = 0;
							if(id == 0) {
								System.out.println("ID indisponivel! Tente novamente.");
								break;
							}
							if(gb.pesquisarProduto(id) != null) {
								System.out.println("ID já está em uso! Tente novamente.");
								break;
							}

							System.out.println("Nome do novo produto");
							String nome = sc.nextLine();
							System.out.println("Tipo do produto:");
							System.out.println(" 1 - Parcela");
							System.out.println(" 2 - Elementar");
							System.out.println(" 3 - Composto");
							int opcao = sc.nextInt();
							sc.nextLine();
							if(opcao !=3 && opcao !=2 && opcao !=1 ) {
								System.out.println("Opção invalida! Tente outra vez.");
								break;
							}
							if(opcao == 3) {
								if(!gb.pesquisarSeExiste()) {
									System.out.println("Não existem parcelas sufeciente no sistema para defenir um Composto");
									break;
								}
							}
							if(opcao == 1 || opcao == 2) {
								System.out.println("Quantidade em stock do produto: ");
								stock = inserir(sc);
								if(stock<0) {
									System.out.println("Numero de Stock invalido! Tente novamente");
									break;
								}
								System.out.println("Quantos meses de validade tem o produto");
								validade = inserir(sc);
								if(validade<0) {
									System.out.println("Validade invalida! Tente novamente");
									break;
								}
							}
							if(opcao == 2 || opcao == 3) {
								System.out.println("Preço do produto (€): ");
								preco = sc.nextDouble();
								sc.nextLine();
								if(preco <= 0) {
									System.out.println("Erro! Preço tem de ser superior a 0.");
									break;
								}
							}else{
								gb.adicionarParcela(id,nome,stock, validade);
								System.out.println("Parcela adicionada com sucesso!\n");
								break;
							}
							if(opcao == 2) {
								gb.adicionarProduto(id, nome, preco, stock, validade, opcao);
							}else {
								gb.adicionarProduto(id, nome, preco, 0, 0, opcao);
								boolean testa = false;
								int i = 0;
								do {
									System.out.println("Qual é o ID da parcela (Insira 0 para parar de adicionar):");
									idParce = inserir(sc);
									if(i >=2) {
										testa = true;
									}
									if(idParce == 0) {
										if(gb.verficarQuantidadeParce(id)) {
											break;
										}
										System.out.println("Precisa de 2 ou mais parcelas para poder defenir o produto composto");
										continue;
									}
									if(gb.pesquisarParcela(idParce) == null) {
										System.out.println("Ou o ID inserido não corresponde a um ID do sistema ou esse produto não é uma parcela.");
										continue;
									}
									if(!gb.pesquisarJaUsado(id, idParce)) {
										System.out.println("Esta parcela já foi usada");
										continue;
									}
									System.out.println("Quantidade da parcela usada neste produto:");
									double qtd = sc.nextDouble();
									sc.nextLine();
									if(qtd <= 0) {
										System.out.println("Quantidade colocada invalida!:");
										continue;
									}
									gb.adiconarNoComposto(id,idParce,qtd);
									i++;
								}while(idParce != 0);
								if(!testa) {
									gb.removerProduto(id);
								}
							}
							break;
						/** Imprime todos os produtos registados no sistema. */	
						case 2:
							System.out.println("------ LISTA DE PRODUTOS ------");
							gb.imprimirProdutos();
							System.out.println("-------------------------------\n");
							break;
						/** Imprime o ID, nome e preço de cada produto. */	
						case 3:
							System.out.println("------ LISTA DE PREÇOS ------");
							gb.imprimirPreços();
							System.out.println("-----------------------------\n");
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
								if(gb.pesquisarParcela(id) == null) {
									System.out.print("Novo preço do produto: ");
									double novoPreco = sc.nextDouble();
									sc.nextLine();
									System.out.println();
									
									gb.atualizarPreco(id, novoPreco);
								}else {
									System.out.println("Preço atualizado com sucesso!\n");
								}
							} else{
									System.out.println("[ERRO] Parcelas não têm preço associado.\n");
							}
							break;
						/** Adiciona stock a um produto existente.
					     * Solicita o ID do produto, a quantidade a adicionar e a validade em meses do novo lote.
					     * Verifica se o produto existe antes de adicionar.
					     */	
						case 5:
							System.out.print("ID do produto: ");
							id = inserir(sc);
							System.out.println();
							if(gb.pesquisarProduto(id) !=null) {
								if(!gb.pesquisarComposto(id)) {
									System.out.println("Quantidade a adicionar em stock:");
									int quant = inserir(sc);
									System.out.println("Validade em meses do novo lot:");
									int val = inserir(sc);
								gb.adicionarStock(id, quant, val);
								}
								else {
									int i = gb.imprimirConteudos(id);
									System.out.println("Quantidade a adicionar em stock:");
									int quant = inserir(sc);
									if(quant > i || quant <=0) {
										System.out.println("Quantidade impossivel de adiconar!");
										break;
									}
									gb.adicionarStock(id, quant);
								}
							}else {
								System.out.println("Esse id não esta atribuido a nenhum produto! Tente outra vez!");
							}
							break;
							
						case 6: 
							gb.imprimirRelatorio();
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
				
				// ==========================================
				// MENU FUNCIONÁRIO DO BAR
				// ==========================================

				if(opc == 3){
					do {
						System.out.println("==========================================");
						System.out.println("         MENU - FUNCIONÁRIO DO BAR       ");
						System.out.println("==========================================");
						System.out.println("1- Consultar Produtos Disponíveis");
						System.out.println("2- Registrar pedido");
						System.out.println("3- Consultar reservas pendentes");
						System.out.println("4- Confirmar reserva");
						System.out.println("5- Confirmar Levantamento de Reserva");
						System.out.println("------------------------------------------");
						System.out.println("10- Sair para login");
						System.out.println("0- Encerrar programa");
						System.out.println("================================");
						System.out.print("Opção:");
						escolha = inserir(sc);
						System.out.println();
						
						switch (escolha) {
						/** Consulta e imprime todos os produtos com stock disponível (stock > 0). */
						case 1:
							gb.consultarProdutosDisponiveis(); 
							break;
						/**
					 	* Regista um novo pedido de balcão para o funcionário autenticado.
					 	* Solicita produtos e quantidades em ciclo até o utilizador inserir 0.
					 	* Permite editar quantidades antes de confirmar.
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
								
								if (p == null || gb.pesquisarParcela(idP)!=null) {
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
								
								Pedido pedido = gb.pesquisarPedido(idPedido);
                                int qtdJaSelecionada = pedido.procurarUtilizado(idP);
                                
                                if (qtd + qtdJaSelecionada > stockAtual) {

									System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
									continue;
								}
								if(pd.verificarJaExiste(idP)) {
									pd.acrescentarMais(idP,qtd);
								}else {
								gb.adicionarNoPedido(idP, qtd, pd);
								tenta = true;
								}
								
							} while (idP!=0);
							
							if(!tenta) {
								gb.apagarPedido(idPedido, uti);
								idPedido -=1;
							} else {
								int alteracao;
								System.out.println("----- O seu Pedido ----");
								pd.imprimirPedido();
								System.out.println("-----------------------");
								do {
									System.out.println("\nDeseja fazer alterações (reduzir/aumentar numero de itens) do seu pedido:");
									System.out.println("1-Sim \n2-Não");
									alteracao = inserir(sc);
									if(alteracao != 1 && alteracao != 2) {
										System.out.println("Opção Invalida! Tente novamente");
										continue;
										
									}else if(alteracao == 1) {
										do {
											pd.imprimirPedido();
											System.out.println("Qual id do produto que quer alterar (insira 0 para parar de editar)");
											idP = inserir(sc);
											if(idP == 0) {
												break;
											}
											if(!pd.consultarItensPedido(idP)) {
												System.out.println("ID do produto não encontrado! Tente outra vez.");
												continue;
											}
											System.out.println("Para que quantidade quer alterar?");
											int qtd = inserir(sc);
											if(qtd <= 0) {
												System.out.println("Valor de quantidade Invalido");
												continue;
											}
											Produto p = gb.pesquisarProduto(idP);
											int stockAtual = p.getStock();
											if (qtd > stockAtual) {
												System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
												continue;
											}
											pd.trocarQuantidade(idP,qtd);
											alteracao = 2;
										}while (idP !=0 );
									}
								}while (alteracao != 2);
								
							
								System.out.println("==========================================");
								System.out.println("              PAGAMENTO                  ");
								System.out.println("==========================================");
								System.out.printf("Total a pagar: %.2f €\n", pd.getTotal());
								
								System.out.println("------------------------------------------");
								int metodoPagamento;
								do {
									System.out.println("1 - Dinheiro");
									System.out.println("2 - Multibanco");
									System.out.print("Método de pagamento: ");
									metodoPagamento = inserir(sc);
									
									if (metodoPagamento == 1) {
										pagamentoDinheiro(sc, pd.getTotal());
									} else if (metodoPagamento == 2) {
										pagamentoMultibanco(sc);
									} else {
										System.out.println("Opção inválida! Por favor, escolha 1 para Dinheiro ou 2 para Multibanco.\n");
									}
								} while (metodoPagamento != 1 && metodoPagamento != 2);
								
								System.out.println("=============================");
								
								
								pd.confirmarPedido();
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
							break;
						
							/* *Confirma o levantamento de uma reserva já confirmada.
					 		* Opção 1: processa o pagamento e marca como LEVANTADA.
					 		* Opção 2: marca como NÃO LEVANTADA e aplica multa ao cliente se não pagou.
					 		*/
						case 5:
							System.out.println("------ RESERVAS CONFIRMADAS ------");
							gb.imprimirReservasConfirmadas();
							System.out.println("----------------------------------");
							System.out.println("Qual reserva pretende selecionar?");
							int idReserva = inserir(sc);
							if(!gb.reservaValida(idReserva)) {
								System.out.println("Essa Reserva é Invalida ser levantada");
								continue;
							}
							System.out.println("------ TIPO DE LEVANTAMENTO ------");
							System.out.println(" 1 - Levantar Encomenda");
							System.out.println(" 2 - Marcar como Não Levantada");
							System.out.println("----------------------------------");
							int tipo = inserir(sc);
							Reserva r = gb.pesquisarReserva(idReserva);
							
							switch (tipo) {
							case 1:
								if (r.getEstadoPagamento() == EstadoPagamento.NAO_PAGO) {
									double totalLevantamento = r.getTotal();
									System.out.println("==========================================");
									System.out.println("              PAGAMENTO                  ");
									System.out.println("==========================================");
									System.out.printf(" Total da reserva: %.2f €%n", totalLevantamento);
									
									int metodoPagamento;
									
									do {
										System.out.println("1 - Dinheiro");
	                                    System.out.println("2 - Multibanco");
	                                    System.out.print("Método de pagamento: ");
	                                    
	                                    metodoPagamento = inserir(sc);
	                                    if (metodoPagamento == 1) {
	                                        pagamentoDinheiro(sc, r.getTotal());
	                                    } else if (metodoPagamento == 2) {
	                                        pagamentoMultibanco(sc);
	                                    } else {
	                                        System.out.println("Opção inválida! Por favor, escolha 1 para Dinheiro ou 2 para Multibanco.\n");
	                                    }
                                } while (metodoPagamento != 1 && metodoPagamento != 2);
									
									
									r.setEstadoPagamento(EstadoPagamento.PAGO); 
								} else {
									System.out.println("\nEsta reserva já foi paga antecipadamente. Pode entregar os artigos.");
								}
								
								r.setEstado(EstadoReserva.LEVANTADA);
								System.out.println("Reserva Levantada com sucesso!");
								break;
								
							case 2:
								
								if(LocalDateTime.now().isBefore(r.getDataHora().plus(30, ChronoUnit.MINUTES))) {
									System.out.println("Ainda não pode marcar como não levantada. Faltam os 30 min de tolerância.");
									continue;
								}
								r.marcarComoNaoLevantada();
								
								
								if (r.getEstadoPagamento() == EstadoPagamento.NAO_PAGO) {
									Cliente cliMultado = gb.pesquisarClientePorReserva(idReserva);
									if (cliMultado != null) {
										double valorMulta = r.getTotal();
										cliMultado.setCredito(cliMultado.getCredito() - valorMulta);
										System.out.printf("Multa de %.2f € aplicada ao cliente %s!\n", valorMulta, cliMultado.getNome());
									}
								}
								break;
								
							default:
								System.out.println("Opção Invalida!");
								break;
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
						default:
							System.out.println("Opção Inválida! Tente outra vez");
						}
					}while(escolha != 0 && escolha != 10);
				}
				// ==========================================
				// MENU CLIENTE
				// ==========================================
				if(opc == 4){
					do {
						System.out.println("==========================================");
						System.out.println("             MENU - CLIENTE              ");
						System.out.println("==========================================");
						System.out.println(" 1 - Fazer Pré-Reserva");
						System.out.println(" 2 - Consultar Reservas");
						System.out.println(" 3 - Cancelar Reserva");
						System.out.println(" 4 - Pagar Multas Pendentes");
						System.out.println("------------------------------------------");
						System.out.println(" 10 - Sair para o Login");
						System.out.println(" 0  - Encerrar Programa");
						System.out.println("==========================================");
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
							Utilizador utilizadorAtual = gb.pesquisarUtilizador(uti);
							Cliente clienteAtual = (Cliente) utilizadorAtual;
							if (clienteAtual.getCredito() < 0) {
								System.out.printf("Operação Bloqueada! Tem crédito negativo: %.2f €\n", clienteAtual.getCredito());
								System.out.println("Por favor, regularize as suas multas (Opção 4) antes de efetuar novas reservas.");
								break; 
							}
						
							boolean tenta = false;
							int idP;
							if(!gb.consultarProdutosDisponiveis()) {
								break;
							}
							System.out.println("--- FAZER PRÉ-RESERVA ---");
							System.out.println("Quando quer recolher (Introduza na forma de ano-mes-dia Hora:min):");
							String input = sc.nextLine();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
							LocalDateTime dt;
							try {
								dt = LocalDateTime.parse(input, formatter);
							}catch (DateTimeParseException e) {
								System.out.println("Data Invalida!");
								break;
							}
							if(dt.isBefore(LocalDateTime.now().plus(30, ChronoUnit.MINUTES)) || dt.isAfter(LocalDateTime.now().plus(7, ChronoUnit.DAYS) )){
								System.out.println("A data inserida tem de ser abaixo de 7 dias ou acima de 30 minuntos.");
								break;
							}
							idPedido += 1;
							Reserva r = gb.criarReserva(uti,idPedido, dt);
							do {
								System.out.println("Id do produto a utilizar (insira '0' para parar de adicionar):");
								idP = inserir(sc);
								
								if(idP == 0) {
									break;
								}
								if(gb.pesquisarProduto(idP) == null || gb.pesquisarParcela(idP)!=null) {
									System.out.println("Produto não encontrado! Tente Novamente");
									continue;
								}
								System.out.println("Quantidade:");
								int qtd = inserir(sc);
								if(qtd <= 0) {
									System.out.println("Erro! Quantidade tem de ser superiror a 0.");
									System.out.println("Tente outra vez.");
									continue;
								}else if(!gb.verificarStock(idP, qtd)){
									System.out.println("Quantidade pedida acima do stock!");
									System.out.println("Tente outra vez.");
									continue;
								}
								
								Produto p = gb.pesquisarProduto(idP);								
								int stockAtual = p.getStock();
                                Pedido pedido = gb.pesquisarPedido(idPedido);
                                int qtdJaSelecionada = pedido.procurarUtilizado(idP);
                       
                                if (qtd + qtdJaSelecionada > stockAtual) {
                                    System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
                                    continue;
                                }
								if(r.verificarJaExiste(idP)) {
									r.acrescentarMais(idP,qtd);
								}else {
									gb.adicionarNaReserva(idP,qtd,r);
								}
								tenta = true;
							}while(idP != 0);
							if(!tenta) {
								gb.apagarReserva(idPedido,uti);
								idPedido -=1;
							}else {
								Pedido p = gb.pesquisarPedido(idPedido);
								int alteracao;
								System.out.println("----- O seu Pedido ----");
								r.imprimirPedido();
								System.out.println("-----------------------");
								do {
									System.out.println("\nDeseja fazer alterações (reduzir/aumentar numero de itens) do seu pedido:");
									System.out.println("1-Sim \n2-Não");
									alteracao = inserir(sc);
									if(alteracao != 1 && alteracao != 2) {
										System.out.println("Opção Invalida! Tente novamente");
										continue;
										
									}else if(alteracao == 1) {
										do {
											p.imprimirPedido();
											System.out.println("Qual id do produto que quer alterar (insira 0 para parar de editar)");
											idP = inserir(sc);
											if(idP == 0) {
												break;
											}
											if(!r.consultarItensPedido(idP)) {
												System.out.println("ID do produto não encontrado! Tente outra vez.");
												continue;
											}
											System.out.println("Para que quantidade quer alterar?");
											int qtd = inserir(sc);
											if(qtd <= 0) {
												System.out.println("Valor de quantidade Invalido");
												continue;
											}
											Produto prod = gb.pesquisarProduto(idP);
											int stockAtual = prod.getStock();
											if (qtd > stockAtual) {
												System.out.println("Erro: Quantidade indisponível! Stock atual: " + stockAtual);
												continue;
											}
											r.trocarQuantidade(idP,qtd);
											alteracao = 2;
										}while (idP !=0 );
									}
								}while (alteracao != 2);
								
								double totalReserva = r.getTotal();
								System.out.println("==========================================");
								System.out.println("         PAGAMENTO DA RESERVA            ");
								System.out.println("==========================================");
								System.out.printf(" Total da reserva: %.2f €%n", totalReserva);
								System.out.println("------------------------------------------");
								System.out.println(" 1 - Pagar agora (Multibanco)");
								System.out.println(" 2 - Pagar no levantamento");
								System.out.println("   [AVISO] Não levantar resultará em multa do valor total!");
								System.out.println("------------------------------------------");
								System.out.print(" Opção: ");
								
								int tipoPagamentoReserva = inserir(sc);
								if (tipoPagamentoReserva == 1) {
									pagamentoMultibanco(sc);
									r.setEstadoPagamento(EstadoPagamento.PAGO);
								} else {
									System.out.println("\nReserva registada com pagamento pendente.");
								}
								System.out.println("========================================");
								
								p.confirmarPedido();
								System.out.println("O pedido foi registrado!");
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
						
						case 4:
							System.out.println("==========================================");
							System.out.println("         CONSULTAR E PAGAR MULTAS        ");
							System.out.println("==========================================");
							Utilizador uCli = gb.pesquisarUtilizador(uti);
							Cliente cCli = (Cliente) uCli;
							
							if (cCli.getCredito() >= 0) {
								System.out.println("Não tem multas pendentes. O seu saldo está regularizado.");
								break;
							}
							
							double divida = -cCli.getCredito();
							System.out.printf("O seu saldo está negativo. O valor total a pagar: %.2f €\n", divida);
							
							int metPag;
							do {
								System.out.println("1 - Pagar com Dinheiro");
								System.out.println("2 - Pagar com Multibanco");
								System.out.print("Selecione o método de pagamento: ");
								metPag = inserir(sc);
								
								if (metPag == 1) {
									pagamentoDinheiro(sc, divida);
									cCli.setCredito(0.0); 
									System.out.println("Dívida liquidada! Agora já pode criar novas reservas.");
								} else if (metPag == 2) {
									pagamentoMultibanco(sc);
									cCli.setCredito(0.0); 
									System.out.println("Dívida liquidada! Agora já pode criar novas reservas.");
								} else {
									System.out.println("Opção inválida! Escolha 1 ou 2.\n");
								}
							} while (metPag != 1 && metPag != 2);
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

	/**
	 * Lê e valida um endereço de email do Scanner.
	 * Verifica se o formato é válido e se o email já existe no sistema.
	 * Repete a leitura até ser inserido um email válido e único.
	 * @param sc - Scanner de entrada
	 * @param gb - instância do GerirBar para verificar emails duplicados
	 * @return o email válido inserido pelo utilizador
	 */
	public static String inserirEmail(Scanner sc,GerirBar gb) {
		String patron = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		String mail=null;
		while(mail==null) {
		
			String a=sc.nextLine().trim();
			if(a.matches(patron)) {
				mail=a;
			}else {
				System.out.println("Email invalido, inserir no seguinte formato: utilizador@gmail.com\nInsira de novo:\n");
				continue;
			}
			if(gb.pequisarEmail(mail)!=null) {
				System.out.println("Email já em utilização\nInsira de novo:\n");
				mail=null;
				continue;
			}
		}
		
		return mail;
	}
	/**
	 * Lê um número decimal (double) do Scanner com proteção contra entradas inválidas.
	 * Usado principalmente nos processos de pagamento.
	 * @param sc - Scanner de entrada
	 * @return o valor decimal inserido pelo utilizador
	 */

	public static double inserirDouble(Scanner sc) { // serve para os valores cujo o int já é iusuficiente,  neste momento a ser usado apenas para os pagamentos
		while(true) {
			try {
				double a = sc.nextDouble();
				sc.nextLine(); // Limpar o buffer do Scanner
				return a;
			} catch(InputMismatchException e) {
				System.out.println("Formato inválido. Insira apenas números (use a vírgula para decimais):\n");
				sc.nextLine(); // Limpar o buffer incorreto
				continue;
			}
		}
	}
	/**
	 * Simula o processo de pagamento por Multibanco.
	 * Solicita número do cartão, validade, CVV e nome do titular.
	 * @param sc - Scanner de entrada
	 */
	public static void pagamentoMultibanco(Scanner sc) {
		System.out.println("------ PAGAMENTO MULTIBANCO ------");
		System.out.print(" Número do cartão: ");
		String num = sc.nextLine();
		System.out.print(" Validade do cartão (ex: 12/26): ");
		String validade = sc.nextLine();
		System.out.print(" Código de segurança (CVV): ");
		String cvv = sc.nextLine();
		System.out.print(" Nome do titular: ");
		String titular = sc.nextLine();
		System.out.println("----------------------------------");
		System.out.println(" A processar pagamento...");
		System.out.println(" Pagamento aprovado! Obrigado, " + titular + ".");
		System.out.println("----------------------------------\n");
	}
	/**
	 * Simula o processo de pagamento em dinheiro.
	 * Solicita o valor entregue e calcula o troco.
	 * Repete a leitura se o valor entregue for insuficiente.
	 * @param sc    - Scanner de entrada
	 * @param total - valor total a pagar
	 */

	public static void pagamentoDinheiro(Scanner sc, double total) {
		System.out.println("------ PAGAMENTO EM DINHEIRO ------");
		System.out.printf(" Total a pagar: %.2f €%n", total);
		System.out.println("-----------------------------------");
		double valorEntregue = 0;
		do {
			System.out.print(" Valor entregue: ");
			valorEntregue = inserirDouble(sc);
			if (valorEntregue < total) {
				System.out.printf("Valor insuficiente! Faltam %.2f €.%n", (total - valorEntregue));
			}
		} while (valorEntregue < total);
		double troco = valorEntregue - total;
		System.out.printf(" Pagamento validado! Troco: %.2f €%n", troco);
		System.out.println("-----------------------------------\n");
	}
}