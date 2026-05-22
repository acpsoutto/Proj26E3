package proj26E3;

public class ItemReserva {

    private int quantidade;
    private double precoUnitario;
    private Produto produto;

    /**
     * CONSTRUTOR
     * @param produto - o produto que está a ser reservado
     * @param quantidade - quantas unidades o cliente quer reservar
     */
    public ItemReserva(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco(); // captura o preço no momento da reserva
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Calcula o subtotal deste item (preço x quantidade)
     */
    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "  - " + produto.getNome() + " x" + quantidade + " @ " + precoUnitario + "€ = " + calcularSubtotal() + "€";
    }
}