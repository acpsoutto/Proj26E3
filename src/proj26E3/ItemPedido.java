/**
 * 
 */
package proj26E3;

import java.util.ArrayList;

/**
 * Representa um item individual dentro de um pedido.
 */
public class ItemPedido {

	private int quantidade;
	private Produto produto;
	private ArrayList<Lote> lotes;
	
	
	/**
	 * CONSTRUTOR
	 * @param produto - produto
	 * @param quantidade - quantidade de unidades pedidas  
	 * precoUnitario - captura o preço no momento do pedido
	 */
	public ItemPedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPreco(); 
		
		}

	/**
	 * GET devolve a quantidade de unidades do produto 
	 * @return quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * GET devovle o preço unitario por produto
	 * @return precoUnitario
	 */
	public double getPrecoUnitario() {
		return precoUnitario;
	}

	/**
	 * GET devolve o produto 
	 * @return produto
	 */
	public Produto getProduto() {
		return produto;
	}

    /**
     * calcula o subtotal do preço * quantidade de produto
     * @return valor (precoUnitario * quantidade)
     */
    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }

	/**
	 * reduz a quantidade de produto no stock
	 */
	public void reduzir() {
		Produto p = getProduto();
		int quantidade = getQuantidade();
		p.reduzirStock(quantidade);
	}
    
    



}
