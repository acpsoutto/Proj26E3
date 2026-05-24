package proj26E3;
/**
 * Class Reserva - Representa uma reserva efetuada por um (CLIENTE).
 */
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {

    private int id;
    private EstadoReserva estado; // Estado da reserva
    private LocalDateTime dataHora;//Data e hora que a reserva foi marcada
    private ArrayList<ItemReserva> itensR;//Lista de itens que compoem a reserva
    
    /**
     * CONSTRUTOR
     * @param id - identificador único da reserva
     * @param dt - data e hora prevista do levantamento
     */
 
    public Reserva(int id, LocalDateTime dt) {
        this.id = id;
        this.estado = EstadoReserva.PENDENTE;// toda a reserva começa pendente
        dataHora = dt;  
        itensR = new ArrayList<>();// começa vazia, items são adicionados depois
    }
    
    
    /**
     * Adiciona um produto à reserva e regista os lotes de stock consumidos.
     * @param produto - o produto a reservar
     * @param quantidade - quantas unidades a reservar
     */
    public void adicionarItem(Produto produto, int quantidade) {
        ItemReserva item = new ItemReserva(produto, quantidade);
        itensR.add(item);
        item.registarStockeVal(quantidade);
    }

    /**
     * Calcula o total da reserva, somando os subtotais de todos os itens.
     * @return total 
     */
    public double calcularTotal() {
        double total = 0;
        for (ItemReserva item : itensR) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /**
     *Confirma a reserva, se estiver no estado (PENDENTE).
     *Caso contrário, informa que a operação não é possível.
     */
    public void confirmar() {
        if (estado == EstadoReserva.PENDENTE) {
            estado = EstadoReserva.CONFIRMADA;
            System.out.println("Reserva " + id + " confirmada com sucesso!");
        } else {
            System.out.println("Não é possível confirmar. Estado atual: " + estado);
        }
    }

    /**
     * Cancela a reserva, se estiver no estado (PENDENTE).
     * Repõe o stock dos produtos reservados e impede o cancelamento
     * de reservas já confirmadas, canceladas ou levantadas.
     * Cancela a reserva — só funciona se não estiver já LEVANTADA
     */
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
            System.out.println("Reserva " + id + " cancelada.");
        }
    }
    
    /**
     * Repõe no stock os itens de todos os (ItemReserva) desta reserva.
     * Deve ser chamado internamente ao cancelar a reserva.
     */
    public void reporItens() {
    	for(ItemReserva i: itensR) {
    		i.repor();
    	}
    }
    
    /**
     * GET devolve o identificador(ID) da reserva
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * GET - devolve o estado da reserva
     * @return estado
     */
    public EstadoReserva getEstado() {
        return estado;
    }

    /**
     * GET - devolve a data e a hora da reserva
     * @return dataHora
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * GET devolve os itens da reserva
     * @return itensR
     */
    public ArrayList<ItemReserva> getItensR() {
        return itensR;
    }

    /**
     *toString - devolve as informaçoes sobre a reserva
     */
    @Override
    public String toString() {
        return "Reserva #" + id 
            + " | Estado: " + estado 
            + " | Data: " + dataHora 
            + " | Total: " + calcularTotal() + "€"
            + " | Itens: " + itensR;
    }
}
