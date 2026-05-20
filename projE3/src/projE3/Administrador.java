package projE3;

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
	
	public void registarFuncionario(Funcionario f) {}
	
}
