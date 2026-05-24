package proj26E3;
/*
 * Class funcionarioBar extends da Class Utilizador, (funcionarioBar é um tipo de utilizador)
 */
import java.util.ArrayList;

public class FuncionarioBar extends Utilizador {
	
	private ArrayList<Pedido> pedidos;

	/**
	 * CONSTRUTOR 
	 * @param numero - numero identificador do funcionario bar
	 * @param nome - nome do funcionario bar
	 * @param email - endereço de email do funcionario bar
	 * @param pw - codigo de acesso do funcionario bar
	 * @param tipo - tipo de utilizador (FUNCIONARIO_BAR)
	 */
	public FuncionarioBar(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		super(numero, nome, email, pw, tipo);
		pedidos = new ArrayList<>();
	}
	
	/**
	 * toString - Devolve as informações do funcionário.
     */
	@Override
	public String toString() {
		return "Funcionario : Funcionario do Bar\n  " + super.toString();
	}
	/**
	 * Adiciona um pedido á lista de pedidos do funcionario
	 * @param p - pedido a adicionar
	 */
	public void adicionarPedido(Pedido p) {
		pedidos.add(p);
	}

	/**
	 * Remover o pedido da lista (pedidos) caso o pedido esteja em branco.
	 * @param idPedido -identificador do pedido
	 */
	public void apagarPedido(int idPedido) {
		for (Pedido p: pedidos) {
			if(p.getId()==idPedido) {
				pedidos.remove(p);
			}
		}
	}
	


	
	
	

}
