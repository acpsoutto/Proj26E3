package proj26E3;
/**
 * Class Reserva - Representa uma reserva efetuada por um (CLIENTE).
 */
import java.time.LocalDateTime;

public class Reserva extends Pedido {
    private EstadoReserva estado; // Estado da reserva
    private EstadoPagamento estadoPagamento;
        /**
         * CONSTRUTOR
         * @param id                   - identificador único da reserva
         * @param dataHoraLevantamento - data e hora prevista do levantamento
         */
    public Reserva(int id, LocalDateTime dataHoraLevantamento) {
       super(id, dataHoraLevantamento);
       this.estado = EstadoReserva.PENDENTE;
       this.estadoPagamento = EstadoPagamento.NAO_PAGO;
        }
	
	public void setEstado(EstadoReserva estado) {
			this.estado = estado;
		}


    public void confirmar() {
        if (estado == EstadoReserva.PENDENTE) {
            estado = EstadoReserva.CONFIRMADA;
            System.out.println("Reserva " + getId() +" confirmada com sucesso!");
        } else {
            System.out.println("Não é possível confirmar. Estado atual: " + estado);
        }
    }

    public void cancelar() {
        if (estado == EstadoReserva.LEVANTADA ) {
            System.out.println("Não é possível cancelar uma reserva já levantada.");
        } else if(estado == EstadoReserva.CANCELADA){
        	 System.out.println("Não é possível cancelar uma reserva já cancelada.");
        }else if(estado == EstadoReserva.CONFIRMADA ){
        	System.out.println("Não é possível cancelar uma reserva já confirmada.");
        }else if ( estado == EstadoReserva.NAO_LEVANTADA) {	
        	System.out.println("Não é possível cancelar uma reserva marcada como não levantada.");
        }else {
          estado = EstadoReserva.CANCELADA;
           reporItens();
          System.out.println("Reserva " + getId() +" cancelada.");
        }
    }
    

    public EstadoReserva getEstado() {
        return estado;
    }


	@Override
	public String toString() {
		return "Reserva "+getId()
		+"Estado: " + estado 
		+ "DataHoraLevantamento: " + getDataHora() 
		+"Total:" + calcularTotal()+"€"
		+ "Itens: " + getItens();
	}
	
	public void marcarComoNaoLevantada() {
        if (this.estado == EstadoReserva.PENDENTE || this.estado == EstadoReserva.CONFIRMADA) {
            this.estado = EstadoReserva.NAO_LEVANTADA;
            System.out.println("O estado da reserva foi alterado para: NÃO LEVANTADA.");
        } else {
            System.out.println("Não é possível alterar o estado da reserva. A mesma já está: " + this.estado);
        }
    }

	public EstadoPagamento getEstadoPagamento() {
		return estadoPagamento;
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento;
	}

}
