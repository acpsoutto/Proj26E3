package proj26E3;
/**
 * Class Reserva - Representa uma reserva efetuada por um (CLIENTE).
 */
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva extends Pedido {
    private EstadoReserva estado; // Estado da reserva
    private LocalDateTime dataHoraLevantamento;
    private ArrayList<Item>itens;
        /**
         * CONSTRUTOR
         * @param id                   - identificador único da reserva
         * @param dataHoraLevantamento - data e hora prevista do levantamento
         */
    public Reserva(int id, LocalDateTime dataHoraLevantamento) {
       super(id);

       LocalDateTime agora = LocalDateTime.now(); //passar isto para gerirBar
       LocalDateTime minimo = agora.plusMinutes(30);
       LocalDateTime maximo = agora.plusDays(7);

       if (dataHoraLevantamento.isBefore(minimo) || dataHoraLevantamento.isAfter(maximo)) {
           throw new IllegalArgumentException("A reserva deve ser marcada entre 30 minutos e 7 dias a partir de agora." );
       }
            this.estado = EstadoReserva.PENDENTE;
            this.dataHoraLevantamento = dataHoraLevantamento;
            this.itens = new ArrayList<>();
        }
	
	@Override
    public void adicionarItem(Produto produto, int quantidade) {
        Item item = new Item(quantidade, produto);
        itens.add(item);
        item.registarStockVal(quantidade);
    }

	 @Override
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
	 
	 @Override
	    public int getQuantidadeProdutos() {
	        int total = 0;
	        for (Item item : itens) {
	            total += item.getQtd();
	        }
	        return total;
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
        		}else {	
            estado = EstadoReserva.CANCELADA;
            reporItens();
            System.out.println("Reserva " + getId() +" cancelada.");
        }
    }
    
    public void reporItens() {
    	for(Item i: itens){
    		i.repor();
    	}
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public LocalDateTime getDataHoraLevantamento() {
		return dataHoraLevantamento;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "Reserva "+getId()
		+"Estado: " + estado 
		+ "DataHoraLevantamento: " + dataHoraLevantamento 
		+"Total:" + calcularTotal()+"€"
		+ "Itens: " + itens;
	}
	

}
