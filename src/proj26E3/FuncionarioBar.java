package proj26E3;

import java.util.ArrayList;

public class FuncionarioBar extends Utilizador {
	
	private ArrayList<Pedido> pedidos;

	/**
	 * CONSTRUTOR 
	 * @param numeroTrabalhador - numero do funcionario que trabalha no bar
	 * @param nome - nome do funcionario bar
	 * @param email - email do funcionario bar
	 * @param codigoAcesso - codigo de acesso do funcionario bar
	 */
	public FuncionarioBar(int numeroTrabalhador, String nome, String email, String codigoAcesso, TipoUtilizador tipo) {
		super(numeroTrabalhador, nome, email, codigoAcesso, tipo);
		pedidos = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Funcionario : Funcionario do Bar\n  " + super.toString();
	}
	
	


	
	
	

}
