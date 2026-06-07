package proj26E3;
/**
 * Representa uma reserva efetuada por um Cliente.
 * Herda de Pedido (partilha a estrutura de itens, data e cálculo do total).
 * Acrescenta o estado da reserva (EstadoReserva) e o estado de pagamento (EstadoPagamento).
 */
import java.time.LocalDateTime;

public class Reserva extends Pedido {
    private EstadoReserva estado; // Estado da reserva
    private EstadoPagamento estadoPagamento;
        /**
     * Cria uma nova reserva no estado PENDENTE e com pagamento em NAO_PAGO.
     * @param id - identificador único da reserva
     * @param dataHoraLevantamento - data e hora prevista de levantamento pelo cliente
     */
    public Reserva(int id, LocalDateTime dataHoraLevantamento) {
       super(id, dataHoraLevantamento);
       this.estado = EstadoReserva.PENDENTE;
       this.estadoPagamento = EstadoPagamento.NAO_PAGO;
        }

    	
    public EstadoReserva getEstado() {
        return estado;
    }
    
	public void setEstado(EstadoReserva estado) {
			this.estado = estado;
		}
    
	public EstadoPagamento getEstadoPagamento() {
		return estadoPagamento;
	}
	
	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento;
	}
	
	
        /**
        * Confirma a reserva, mudando o estado de PENDENTE para CONFIRMADA.
        * Só é possível confirmar uma reserva que esteja no estado PENDENTE.
        * Se a reserva já estiver noutro estado, informa o utilizador.
        */

    public void confirmar() {
        if (estado == EstadoReserva.PENDENTE) {
            estado = EstadoReserva.CONFIRMADA;
            System.out.println("Reserva " + getId() +" confirmada com sucesso!");
        } else {
            System.out.println("Não é possível confirmar. Estado atual: " + estado);
        }
    }
    
    
    /**
     * Cancela a reserva, mudando o estado para CANCELADA.
     * Só é possível cancelar reservas no estado PENDENTE.
     * Reservas CONFIRMADAS, LEVANTADAS, CANCELADAS ou NAO_LEVANTADAS não podem ser canceladas.
     * Ao cancelar, o stock reservado pelos itens é restituído.
     */

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
           reporItens(); // devolve o stock reservado ao inventário
          System.out.println("Reserva " + getId() +" cancelada com sucesso.");
        }
    }
    
	
    /**
     * Uma reserva no estado confirmada, pode ficar com o estado nao levantada 
     */
    
	public void marcarComoNaoLevantada() {
        if (this.estado == EstadoReserva.CONFIRMADA) {
            this.estado = EstadoReserva.NAO_LEVANTADA;
            System.out.println("O estado da reserva foi alterado para: NÃO LEVANTADA.");
        } else {
            System.out.println("Não é possível alterar o estado da reserva. A mesma já está: " + this.estado);
        }
    }

	
	/**
     * Devolve uma representação textual da reserva com todos os seus detalhes.
     */
    @Override
    public String toString() {
    	String diahora = getDataHora().getDayOfMonth() + "/" + getDataHora().getMonth() + "|" + getDataHora().getHour() + ":" + getDataHora().getMinute();
        return "Reserva #" + getId()
                + " | Estado: " + estado
                + " | Pagamento: " + estadoPagamento
                + " | Levantamento: " + diahora
                + " | Total: " + String.format("%.2f", calcularTotal()) + "€"
                + " | Itens: " + getItens();
    }
}
