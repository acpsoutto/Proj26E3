package proj26E3;
/*
 * Class Cliente representa um cliente do bar, que é um tipo de utilizador.
 * Um cliente pode criar, cancelar, apagar reservas e consultar o 
 * estado das suas reservas pendentes.
 */
import java.util.ArrayList;

public class Cliente extends Utilizador {
	private ArrayList<Reserva> reservas;

	/**
	 * CONSTRUTOR
	 * @param numero - numero identificador do utilizador
	 * @param nome - nome do cliente
	 * @param email - endereço de email do cliente
	 * @param pw - palavra-asse de acesso
	 * @param tipo - tipo de utilizador
	 * @param reservas - reservas do cliente
	 */
	public Cliente(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		super(numero, nome, email, pw, tipo);
		reservas = new ArrayList<>();
	}

	/**
	 * Adiciona uma reserva á lista de reservas do cliente.
	 * @param r
	 */
	public void adicionarReserva(Reserva r) {
		reservas.add(r);
	}

	/**
	 * Cancelar reserva com o ID fornecido se existir na lista do cliente.
	 * @param idReserva
	 */
	public void cancelarReserva(int idReserva) {
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				r.cancelar();
			}
		}
	}

	/**
	 * Cancelar e remover permanentemente a reserva com o ID fornecido.
	 * @param idReserva
	 */
	public void apagarReserva(int idReserva) {
		cancelarReserva(idReserva);
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				reservas.remove(r);
			}
		}
	}
/*
 * Imprime todas as reservas do cliente no terminal
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
	 * @param idReserva - identificador da reserva
	 * @return r - caso encontre a reserva
	 * @return null - caso não encontre
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
	public int consultar() {
		int a= 0;
		for(Reserva r : reservas) {
			if(r.getEstado()== EstadoReserva.PENDENTE) {
				System.out.println(r);
				a  = 1;
			}
		}
		return a;
	}
	
	public double clienteTotalReserva() {
		double total = 0;
		for (Reserva r : reservas) {
			if (r.getEstado() == EstadoReserva.LEVANTADA || r.getEstado()== EstadoReserva.NAO_LEVANTADA) {
				total += r.getTotal();
			}
		}
		return total;
	}
	
	public int numeroPedidos() {
		int total = 0;
		for (Reserva r : reservas) {
			if (r.getEstado() == EstadoReserva.LEVANTADA || r.getEstado()== EstadoReserva.NAO_LEVANTADA) {
				total += 1;
			}
		}
		return total;
	}

}
