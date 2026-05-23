/**
 * 
 */
package proj26E3;

/**
 * 
 */
public class ItemPedido {

	private int quantidade;
	private double precoUnitario;
	private Produto produto;
	
	public ItemPedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPreco(); // captura o preço no momento do pedido
		
		}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }

	public void reduzir() {
		Produto p = getProduto();
		int quantidade = getQuantidade();
		p.reduzirStock(quantidade);
	}
    
    



}
