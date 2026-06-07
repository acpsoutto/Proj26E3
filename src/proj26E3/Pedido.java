package proj26E3;
/**
 * Representa um pedido efetuado por um (FuncionarioBar).
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {

	private int id; //identificador do pedido
	private LocalDateTime data; //data em que o pedido foi criado
	private double total;// total acumulado do pedido
	private ArrayList <Item> itens;

	public Pedido(int id, LocalDateTime data) {
		this.id = id;
		this.data = data;
		this.itens = new ArrayList<>();
	}

	public double getTotal() {
		return calcularTotal();
	}
	
	public int getId() {
		return id;
	}

    public LocalDateTime getDataHora() {
		return data;
	}

    public ArrayList<Item> getItens() {
		return itens;
	}
    
    /**
     * Adiciona um item ao pedido
     * @param produto 
     * @param quantidade 
     */
	public void adicionarItem(Produto produto, int quantidade) {
        Item item = new Item(quantidade, produto);
        itens.add(item);
    }
    
	/**
	 * Calcula o total do pedido
	 * @return total - valor total do pedido
	 */
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
   
   /**
    * Vê a quantidade total de produtos de um pedido
    * @return
    */
   public int getQuantidadeProdutos() { 
        int total = 0;

        for(Item item : itens) {
            total += item.getQtd();
        }

        return total;
    }
   
   /**
    * Verifica se um dado produto ja existe no pedido
    * @param id2 - id do produto
    * @return
    */
   public boolean vericarJaExiste(int id2) {
		for(Item i : itens) {
			if(i.getProduto().getId() == id2) {
				return true;
			}
		}
		return false;
	}
   

   	/**
   	 * Caso o produto ja exista no pedido, acrescenta à quantidade escolhida previamente
   	 * @param id2 - id do produto
   	 * @param qtd - quantidade extra a acrescentar
   	 */
	public void acrescentar(int id2, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId()== id2) {
				i.setQtd(i.getQtd()+qtd);
				return;
			}
		}
	}
	
	/**
	 * Imprime dentro de um pedido todos os seus itens
	 */
	public void imprimirPedido() {
		for(Item i : itens) {
			System.out.println(i.getProduto().getId() +" | "+ i.getProduto().getNome() +" | " + i.getQtd());
		}
	}

	/**
	 * Verifica se o id de um produto já existe no pedido
	 * @param idP - id do produto
	 * @return
	 */
	public boolean consultarItensPedido(int idP) {
		for(Item i : itens) {
			if(i.getProduto().getId() == idP) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Modifica a quantidade de um produto no pedido para uma nova quantidade selecionada
	 * @param idP - id do produto
	 * @param qtd - nova quantidade
	 */
	public void trocarQuantidade(int idP, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId() == idP) {
				i.setQtd(qtd);
				return;
			}
		}
	}
	
	/**
	 * Confirma um pedido 
	 * Chama o método que regista a validade e stock 
	 */
	public void confirmarPedido() {
		for(Item i : itens) {
			i.registarStockVal(i.getQtd());
		}
	}
	
	/**
	 * Acrescenta quantidade a um produto
	 * @param idP - id do produto
	 * @param qtd - quantidade a acrescentar
	 */
	public void acrescentarMais(int idP, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId()== idP) {
				i.setQtd(i.getQtd()+qtd);
				return;
			}
		}
	}
	
	/**
	 * verifica se um produto ja existe no pedido
	 * @param idP - id do produto
	 * @return
	 */
	public boolean verificarJaExiste(int idP) {
		for(Item i : itens) {
			if(i.getProduto().getId()==idP) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Permite repor itens em stock
	 */
	public void reporItens() {
		for(Item i: itens){
			i.repor();
		}
	}
	
	@Override
	public String toString() {
		return "Pedido [Id do pedido: " + id + ", Data e Hora: " + data + ", Valor Total: " + total + ", Itens do Pedido: " + itens + "]";
	}
}
