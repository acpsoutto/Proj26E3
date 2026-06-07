package proj26E3;
/*
 * Class funcionarioBar extends da Class Utilizador, (funcionarioBar é um tipo de utilizador)
 */
import java.util.ArrayList;

public class FuncionarioBar extends Utilizador {
	
	private ArrayList<Pedido> pedidos;

	/**
	 * CONSTRUTOR 
	 * @param numero - numero de identificação do utilizador
	 * @param nome - nome do utilizador
	 * @param email - endereço de email do utilizador
	 * @param pw - codigo de acesso do utilizador
	 * @param tipo - tipo de utilizador
	 * @param pedidos - lista de pedidos associados ao funcionario
	 */
	public FuncionarioBar(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		super(numero, nome, email, pw, tipo);
		pedidos = new ArrayList<>();
	}
	
	public ArrayList<Pedido> getPedidos() {
	    return pedidos;
	}
	

	/**
	 * Adiciona um pedido à lista de pedidos do funcionario
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
	    for (int i = 0; i < pedidos.size(); i++) {
	        if (pedidos.get(i).getId() == idPedido) {
	            pedidos.remove(i);
	            return;
	        }
	    }
	}
	
	/**
	 * Verifica se o id de um produto já foi utilizado num certo pedido
	 * @param id - número de identificação do produto
	 * @param idPedido - número de identificação do pedido
	 */
	public boolean vericarJaExiste(int id, int idPedido) {
		for(Pedido p : pedidos) {
			if(p.getId() == idPedido) {
				if(p.vericarJaExiste(id)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Calcula o valor total dos pedidos do funcionario
	 * @return total - valor total de todos os pedidos do funcionario
	 */
	public double funcionarioTotalPedido() {
		double total = 0;
		for (Pedido p : pedidos) {
			total += p.getTotal();
		}
		return total;
	}
	
	/**
	 * 
	 * @return o número total de pedidos
	 */
	public int numeroPedidos() {
		return pedidos.size();
	}

	
	/**
	 * Pesquisa um pedido pelo seu numero de identificação
	 * @param idPedido - numero identificaçao do pedido
	 * @return
	 */
	public Pedido pesquisarPedido(int idPedido) {
		for(Pedido P : pedidos) {
			if(P.getId()==idPedido) {
				return P;
			}
		}
		return null;
	}
	
	/**
	 * toString - Devolve as informações do funcionário.
     */
	@Override
	public String toString() {
		return "Funcionario : Funcionario do Bar\n  " + super.toString();
	}
}
