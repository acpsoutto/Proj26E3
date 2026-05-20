package proj26E3;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GerirBar gb = new GerirBar();
		CategoriaProduto categoria = null;

		int opc;
		do {
			System.out.println("Seleciona perfil");
			System.out.println("1- Administrador");
			System.out.println("2- Gerente");
			System.out.println("3- Funcionario do Bar");
			System.out.println("4- Cliente");
			System.out.println("0- Terminar Programa");
			System.out.print("Opcação:");
			opc = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			if (opc != 0 && opc != 1 && opc != 2 && opc != 3 && opc != 4 ) {
				System.out.println("Esse perfil não existe! Tente novamente.");
				continue;
			}
			
			if(opc == 0) {
				System.out.println("Obrigado por utilizar o programa.");
				break;
			}
			
			System.out.print("Insira o utilizador:");
			String uti = sc.nextLine();
			System.out.println();
			System.out.print("Insira a palavra-chave:");
			String chave = sc.nextLine();
			System.out.println();
			
			switch(opc) {
			case 1:
				/*
				 * Admnistrador A = gb.pesquisarAdmin();
				 * if(A == null || !A.getCodigoAcesso().equals(chave){
				 * 		System.out.println("Utilizador ou palavra-chave errada. Tente outra vez);
				 * 		continue
				 *	}	
				 */
			case 2://repetir de cima
			case 3: //repetir de cima ou
				/*Funcionario f = gb.pesquisarFuncionario();
				 * if(A == null || !f.getCodigoAcesso().equals(chave){
				 * 		System.out.println("Utilizador ou palavra-chave errada. Tente outra vez);
				 * 		continue
				 *	}	
				 */
				break;
			case 4:
				Cliente c = gb.pesquisarCliente();
				 if(c == null || !c.getCodigoAcesso().equals(chave)){
					 System.out.println("Utilizador ou palavra-chave errada. Tente outra vez");
					  	continue;
					}
				 break;
			}
			
			int escolha;
			if(opc == 1){
				do {
					//Menu do Admnistrador
					//0 - Para encerrar programa 10: voltar login
					switch (escolha) {
					//funcionalidades do Admnistrador
					}
				}while(escolha != 0 || escolha != 10);
			}
			
			if(opc == 2){
				do {
					System.out.println("1- Adicionar Produtos");
					System.out.println("2- Consultar Produtos");
					System.out.println("3- Consultar Preços"); //Menu do Gerente
					System.out.println("4- Atualizar Preços");
					System.out.println("10- Sair para login");
					System.out.println("0- Encerrar programa");
					System.out.print("Opcação:");
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
							double preco = sc.nextDouble();
							sc.nextLine();
							System.out.println();
							
							gb.atualizarPreco(id, preco);
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
				}while(escolha != 0 || escolha != 10);
			}
			if(opc == 3){
				do {
					//Menu do Funcionario do Bar
					//0 - Para encerrar programa 10: voltar login
					switch (escolha) {
					//funcionalidades do funcionario do bar
					}
				}while(escolha != 0 || escolha != 10);
			}
			
			if(opc == 4){
				do {
					//Menu do Cliente
					//0 - Para encerrar programa 10: voltar login
					switch (escolha) {
					//funcionalidades do cliente
					}
				}while(escolha != 0|| escolha != 10);
			}
		}while(opc != 0);
	}
}
