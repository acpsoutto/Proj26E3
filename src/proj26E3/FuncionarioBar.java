package proj26E3;

public class FuncionarioBar extends Funcionario {

	/**
	 * CONSTRUTOR 
	 * @param numeroTrabalhador - numero do funcionario que trabalha no bar
	 * @param nome - nome do funcionario bar
	 * @param email - email do funcionario bar
	 * @param codigoAcesso - codigo de acesso do funcionario bar
	 */
	public FuncionarioBar(int numeroTrabalhador, String nome, String email, String codigoAcesso) {
		super(numeroTrabalhador, nome, email, codigoAcesso);
	}

	@Override
	public String toString() {
		return "|Funcionario : Funcionario do Bar\n " + super.toString();
	}
	
	


	
	
	

}
