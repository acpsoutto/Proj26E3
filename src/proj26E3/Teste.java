package proj26E3;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GerirBar gb = new GerirBar();
		CategoriaProduto categoria = null;

		int opc;
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
			opc = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			/*
			 * INTRODUÇAO DADOS
			 */
			if (opc != 0 && opc != 1 && opc != 2 && opc != 3 && opc != 4 ) {
				System.out.println("Esse perfil não existe! Tente novamente.");
				continue;
			}
			
			if(opc == 0) {
				System.out.println("Obrigado por utilizar o programa.");
				break;
			}
			
			System.out.print("Insira o Número de Identificação:");
			int uti = sc.nextInt(); sc.nextLine();
			System.out.println();
			System.out.print("Insira a palavra-chave:");
			String chave = sc.nextLine();
			System.out.println();
			
			/*
			 * VERIFICAÇÃO DE AUTENTICAÇÃO DO USUARIO
			 */
			
			try {
				Utilizador f = gb.pesquisarUtilizador(uti);
					
				if (f == null || !f.getPw().equals(chave)) {
					System.out.println("Utilizador ou palavra-chave errada. Tente outra vez.\n");
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
					
			} catch (NumberFormatException e) {
				System.out.println("Para funcionários, o utilizador deve ser o número de trabalhador numérico.\n");
				continue;
			}

			
			int escolha = -1; // Inicializado para evitar erros de compilação
			
			if(opc == 1){
				do {
					System.out.println("1- Adicionar Funcionário do bar");
					System.out.println("2- Adicionar Cliente");
					System.out.println("3- Adicionar Gerente");
					System.out.println("4- Adicionar Admnistrador");
					System.out.println("10- Sair para login");
					System.out.println("0- Encerrar programa");
					System.out.print("Opção:");
					escolha = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (escolha) {
					case 1: 
						System.out.println("ID do Funcionário:");
						int id = sc.nextInt();
						sc.nextLine();
						
						if (gb.pesquisarUtilizador(id)!= null) {
							System.out.println("ID já existe.");
							continue;
						}
						System.out.println("Email do Funcionário:");
						String mail = sc.next();
						
						System.out.println("PassWord do Funcionário:");
						String pw = sc.next();
						
						System.out.println("Nome do Funcionário:");
						String nome = sc.next();
						
						TipoUtilizador tipo = TipoUtilizador.FUNCIONARIO_BAR;
						
						gb.adicionarUtilizador(id, mail, pw, nome, tipo);
						System.out.print("Funcionário adicionado");
						break;
						
					case 2:
						System.out.println("ID do Cliente:");
						id = sc.nextInt();
						sc.nextLine();
						
						if (gb.pesquisarUtilizador(id)!= null) {
							System.out.println("ID já existe.");
							continue;
						}
						System.out.println("Email do Cliente:");
						mail = sc.next();
						
						System.out.println("PassWord do Cliente:");
						pw = sc.next();
						
						System.out.println("Nome do Cliente:");
						nome = sc.next();
						
						tipo = TipoUtilizador.CLIENTE;
						
						gb.adicionarUtilizador(id, mail, pw, nome, tipo);
						System.out.print("Cliente adicionado");
						break;
						
					case 3: 
						System.out.println("ID do Gerente:");
						id = sc.nextInt();
						sc.nextLine();
						
						if (gb.pesquisarUtilizador(id)!= null) {
							System.out.println("ID já existe.");
							continue;
						}
						System.out.println("Email do Gerente:");
						mail = sc.next();
						
						System.out.println("PassWord do Gerente:");
						pw = sc.next();
						
						System.out.println("Nome do Gerente:");
						nome = sc.next();
						
						tipo = TipoUtilizador.GERENTE;
						
						gb.adicionarUtilizador(id, mail, pw, nome, tipo);
						System.out.print("Gerente adicionado");
						break;
					case 4: 
						System.out.println("ID do Admnistrador:");
						id = sc.nextInt();
						sc.nextLine();
						
						if (gb.pesquisarUtilizador(id)!= null) {
							System.out.println("ID já existe.");
							continue;
						}
						System.out.println("Email do Admnistrador:");
						mail = sc.next();
						
						System.out.println("PassWord do Admnistrador:");
						pw = sc.next();
						
						System.out.println("Nome do Admnistrador:");
						nome = sc.next();
						
						tipo = TipoUtilizador.ADMNISTRACAO;
						
						gb.adicionarUtilizador(id, mail, pw, nome, tipo);
						System.out.print("Admnistrador adicionado");
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
				} while (escolha != 10 || opc != 0);
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
					System.out.println("10- Sair para login");
					System.out.println("0- Encerrar programa");
					System.out.print("Opção:");
					escolha = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (escolha) {
					case 1:
						System.out.println("ID do novo produto");
						int id = sc.nextInt();
						sc.nextLine();
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
						int stock = sc.nextInt();
						sc.nextLine();
						if(stock<0) {
							System.out.println("Numero de Stock invalido! Tente novamente");
							break;
						}
						System.out.println("Quantos meses de validade tem o produto");
						int validade = sc.nextInt();
						sc.nextLine();
						if(validade<0) {
							System.out.println("Validade invalida! Tente novamente");
							break;
						}
						gb.adicionarProduto(id,nome,preco,categoria,stock, validade);
						break;
						
					case 2:
						gb.imprimirProdutos();
						break;
						
					case 3:
						gb.imprimirPreços();
						break;
						
					case 4:
						System.out.print("Qual o id do produto a nome: ");
						id = sc.nextInt();
						sc.nextLine();
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
					escolha = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (escolha) {
					case 1:
						gb.consultarProdutosDisponiveis(); 
						break;
					case 2:
						gb.registrarPedido();
						System.out.println("O pedido foi registrado!");
						break;
					case 3:
						gb.consultarReservasPendentes();
						break;
					case 4:
						gb.confirmarReserva();
					case 10:
						System.out.println("A sair para o login");
						break;
					case 0:
						System.out.println("Obrigado por utilizar o programa.");
						opc = 0;
						break;
					default:
						System.out.println("Opção Inválida! Tente outra vez");
					}
				}while(escolha != 0 && escolha != 10);
			}
			
			if(opc == 4){
				/*
				do {
					//Menu do Cliente
					//0 - Para encerrar programa 10: voltar login
					switch (escolha) {
					//funcionalidades do cliente
					}
				}while(escolha != 0|| escolha != 10);
				*/
				System.out.println("Menu do Cliente em desenvolvimento...\n");
			}
		}while(opc != 0);
		
		sc.close();
	}
}