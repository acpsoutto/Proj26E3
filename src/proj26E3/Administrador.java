package proj26E3;
import java.util.Scanner;

public class Administrador extends Funcionario{

	/**
	 * @param numeroTrabalhador
	 * @param nome
	 * @param email
	 * @param codigoAcesso
	 */
	public Administrador(int numeroTrabalhador, String nome, String email, String codigoAcesso) {
		super(numeroTrabalhador, nome, email, codigoAcesso);
	}
	
	public void registarFuncionario(GerirBar gerir, Scanner sc) {
		System.out.println("Insira o tipo de funcionário:\n1-Gerente\n2-Vendedor");
		int op=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Insira o numero do Trabalhador: ");
		int nTra=sc.nextInt();
		sc.nextLine();
		System.out.println("Insira o nome: ");
		String nome=sc.nextLine();
		System.out.println("Insira o email: ");
		String mail=sc.nextLine();
		System.out.println("Insira o codigo de acceso: ");
		String codAcc=sc.nextLine();
		
		switch (op) {
			case 1:
				gerir.addFuncionario(new Gerente(nTra,nome,mail,codAcc));
				break;
			case 2:
				
				gerir.addFuncionario(new FuncionarioBar(nTra,nome,mail,codAcc)); 
				break;
		}
	}
	
}
