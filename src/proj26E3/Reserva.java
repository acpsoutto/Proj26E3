package proj26E3;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {

    private int id;
    private EstadoReserva estado;
    private LocalDateTime dataHora;
    private ArrayList<ItemReserva> itensR;
    
    /**
     * CONSTRUTOR
     * @param id - identificador único da reserva
     */
    public Reserva(int id) {
        this.id = id;
        this.estado = EstadoReserva.PENDENTE; // toda a reserva começa pendente
        this.dataHora = LocalDateTime.now();  // regista o momento exato da criação
        this.itensR = new ArrayList<>();      // começa vazia, items são adicionados depois
    }
    
    
    /**
     * Adiciona um produto à reserva
     * @param produto - o produto a reservar
     * @param quantidade - quantas unidades
     */
    public void adicionarItem(Produto produto, int quantidade) {
        ItemReserva item = new ItemReserva(produto, quantidade);
        itensR.add(item);
    }

    /**
     * Calcula o total de todos os itens da reserva
     */
    public double calcularTotal() {
        double total = 0;
        for (ItemReserva item : itensR) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /**
     * Confirma a reserva — só funciona se estiver PENDENTE
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
     * Cancela a reserva — só funciona se não estiver já LEVANTADA
     */
    public void cancelar() {
        if (estado == EstadoReserva.LEVANTADA) {
            System.out.println("Não é possível cancelar uma reserva já levantada.");
        } else {
            estado = EstadoReserva.CANCELADA;
            System.out.println("Reserva " + id + " cancelada.");
        }
    }
    
    public int getId() {
        return id;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public ArrayList<ItemReserva> getItensR() {
        return itensR;
    }

    @Override
    public String toString() {
        return "Reserva #" + id 
            + " | Estado: " + estado 
            + " | Data: " + dataHora 
            + " | Total: " + calcularTotal() + "€"
            + " | Itens: " + itensR;
    }
}
