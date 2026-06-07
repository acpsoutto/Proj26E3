package proj26E3;
/**
 * Representa um cliente do bar.
 * Um cliente é um tipo de Utilizador que pode criar, consultar e cancelar reservas.
 * Tem também um sistema de crédito: se não levantar uma reserva confirmada,
 * é aplicada uma multa (crédito negativo) que impede novas reservas até ser paga.
 */
import java.util.ArrayList;

public class Cliente extends Utilizador {
	private ArrayList<Reserva> reservas;
	private double credito;
	
	/**
	 * CONSTRUTOR
	 * @param numero - numero de identificação do utilizador
	 * @param nome - nome do utilizador
	 * @param email - endereço de email do utilizador
	 * @param pw - palavra-passe do utilizador
	 * @param tipo - tipo de utilizador
	 * @param reservas - reservas do cliente
	 * @param credito - credito associado a multas do cliente
	 */
	public Cliente(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		super(numero, nome, email, pw, tipo);
		reservas = new ArrayList<>();
		this.credito= 0.0;
	}
	 /**
     * Devolve a lista de todas as reservas do cliente
     * @return reservas - lista de reservas
     */
	public ArrayList<Reserva> getReservas() {
	    return reservas;
	}
	
	/**
	 * Adiciona uma reserva à lista de reservas do cliente
	 * @param r
	 */
	public void adicionarReserva(Reserva r) {
		reservas.add(r);
	}

	/**
     * Cancela a reserva com o ID indicado, se existir na lista do cliente.
     * O método chama o método cancelar() da classe Reserva.
     * @param idReserva - número de identificação da reserva a cancelar
     */
	public void cancelarReserva(int idReserva) {
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				r.cancelar();
			}
		}
	}

	/**
     * Cancela e remove a reserva com o ID indicado.
     * Usado quando a reserva é criada mas não é adicionado nenhum item à reserva.
     * @param idReserva - número de identificação da reserva a apagar
     */
	public void apagarReserva(int idReserva) {
	    cancelarReserva(idReserva);

	    for(int i = 0; i < reservas.size(); i++) {
	        if(reservas.get(i).getId() == idReserva) {
	            reservas.remove(i);
	            break;
	        }
	    }
	}
/*
 * Imprime todas as reservas do cliente
 */
	public boolean imprimir() {
		if(reservas.isEmpty()) {
			return false;
		}
		for(Reserva r : reservas) {
			System.out.println(r);
		}
		return true;
	}

	/**
	 * Procura e devolve a reserva com o ID indicado.
	 * @param idReserva - número de identificação da reserva
	 * @return r - retorna a reserva com o id indicado, caso ela exista
	 * @return null - retorna vazio caso não encontre
	 */
	public Reserva encontrarReserva(int idReserva) { 
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Consulta se o cliente tem reservas no estado (PENDENTE)
	 * @return a - se encontrar a=1, caso nao a=0
	 */
	public boolean consultar() {
		boolean a= false;
		for(Reserva r : reservas) {
			if(r.getEstado()== EstadoReserva.PENDENTE) {
				System.out.println(r);
				a  = true;
			}
		}
		return a;
	}
	/**
     * Calcula o total gasto pelo cliente em reservas já concluídas
     * (estados LEVANTADA ou NAO_LEVANTADA).
     * @return total - retorna o total acumulado de todas as reservas levantadas e nao levantadas
     */
	public double clienteTotalReserva() {
		double total = 0;
		for (Reserva r : reservas) {
			if (r.getEstado() == EstadoReserva.LEVANTADA || r.getEstado()== EstadoReserva.NAO_LEVANTADA) {
				total += r.getTotal();
			}
		}
		return total;
	}
	/**
     * Conta o número de reservas concluídas pelo cliente
     * (estados LEVANTADA ou NAO_LEVANTADA).
     * @return total - retorna o número total de reservas concluídas pelo cliente
     */
	public int numeroPedidos() {
		int total = 0;
		for (Reserva r : reservas) {
			if (r.getEstado() == EstadoReserva.LEVANTADA || r.getEstado()== EstadoReserva.NAO_LEVANTADA) {
				total += 1;
			}
		}
		return total;
	}
	/**
     * Pesquisa uma reserva pelo seu ID na lista do cliente.
     * @param idReserva - identificador da reserva
     * @return retorna a reserva encontrada, ou null se não existir
     */
	public Reserva pesquisarReserva(int idPedido) {
		for(Reserva r : reservas) {
			if(r.getId()== idPedido) {
				return r;
			}
		}
		return null;
	}
	/**
     * Pesquisa um pedido (Reserva tratada como Pedido) pelo seu ID.
     * Usado em contextos onde se trabalha com a classe base Pedido.
     * @param idPedido - número de identificação do pedido
     * @return p - retorna o pedido encontrado (null se não existir)
     */
	public Pedido pesquisarpedido(int idPedido) {
		for(Pedido p : reservas) {
			if(p.getId()== idPedido) {
				return p;
			}
		}
		return null;
	}
	 /**
     * Verifica se um produto (por ID) já existe num pedido/reserva específico do cliente.
     * Usado para evitar duplicados ao adicionar itens.
     * @param id       - ID do produto a verificar
     * @param idPedido - ID do pedido/reserva onde procurar
     * @return true se o produto já existir nesse pedido, false caso contrário
     */
	public boolean vericarJaExiste(int id, int idPedido) {
		for(Pedido p : reservas) {
			if(p.getId() == idPedido) {
				if(p.vericarJaExiste(id)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Imprime todas as reservas do cliente que estão no estado CONFIRMADA.
     * Usado pelo funcionário do bar para ver quais reservas pode marcar como levantadas.
     */
	public void imprimirRervasConfirmadas() {
		for(Reserva r : reservas) {
			if(r.getEstado()== EstadoReserva.CONFIRMADA) {
				System.out.println(r);
			}
		}
	}
	/**
     * Verifica se uma reserva específica do cliente está no estado CONFIRMADA.
     * Usado para validar se uma reserva pode ser levantada.
     * @param idReserva - identificador da reserva a verificar
     * @return true se a reserva existir e estiver CONFIRMADA, false caso contrário
     */
	public boolean verificarReserva(int idReserva) {
		for(Reserva r : reservas) {
			if(r.getEstado()== EstadoReserva.CONFIRMADA && r.getId()== idReserva) {
				return true;
			}
		}
		return false;
	}
	/**
     * Devolve o crédito atual do cliente.
     * Um valor negativo indica dívida (multa por reserva não levantada).
     * @return credito - valor do crédito (negativo = dívida)
     */
	public double getCredito() {
		return credito;
	}
	/**
     * Define o crédito do cliente.
     * Usado para aplicar multas (valor negativo) ou para liquidar dívidas (repor a 0).
     * @param credito - novo valor do crédito
     */
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
}
