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
	
	public ArrayList<Pedido> getPedidos() {
	    return pedidos;
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
	
	
	/**public boolean temOPedido(int idPedido) {
	* for(pedido)
	* return true;
	*/ 
	
	public double funcionarioTotalPedido() {
		double total = 0;
		for (Pedido p : pedidos) {
			total += p.getTotal();
		}
		return total;
	}
	
	public int numeroPedidos() {
		return pedidos.size();
	}

	public Pedido pesquisaPedio(int idPedido) {
		for(Pedido p : pedidos) {
			if(p.getId()== idPedido) {
				return p;
			}
		}
		return null;
	}

	public Pedido pesquisarpedido(int idPedido) {
		for(Pedido P : pedidos) {
			if(P.getId()==idPedido) {
				return P;
			}
		}
		return null;
	}
}
