package proj26E3;

import java.util.ArrayList;

public class Cliente extends Utilizador {
	private ArrayList<Reserva> reservas;

	/**
	 * @param numero
	 * @param nome
	 * @param email
	 * @param pw
	 * @param tipo
	 * @param reservas
	 */
	public Cliente(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		super(numero, nome, email, pw, tipo);
		reservas = new ArrayList<>();
	}

	public void adicionarReserva(Reserva r) {
		reservas.add(r);
	}

	public void cancelarReserva(int idReserva) {
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				r.cancelar();
			}
		}
	}

	public void apagarReserva(int idReserva) {
		cancelarReserva(idReserva);
		for(Reserva r : reservas) {
			if(r.getId()== idReserva) {
				reservas.remove(r);
			}
		}
	}
	
	public boolean imprimir() {
		if(reservas.isEmpty()) {
			return false;
		}
		for(Reserva r : reservas) {
			System.out.println(r);
		}
		return true;
	}

	public Reserva encontrarReserva(int id) {
		for(Reserva r : reservas) {
			if(r.getId()== id) {
				return r;
			}
		}
		return null;
	}

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
	

}
