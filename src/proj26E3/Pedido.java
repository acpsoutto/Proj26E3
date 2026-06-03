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

	public void adicionarItem(Produto produto, int quantidade) {
        Item item = new Item(quantidade, produto);
        itens.add(item);
        //item.reduzir();
    }
    
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
    
   public int getQuantidadeProdutos() { // colocar no diagrama
        int total = 0;

        for(Item item : itens) {
            total += item.getQtd();
        }

        return total;
    }
   
   public boolean vericarJaExiste(int id2) {
		for(Item i : itens) {
			if(i.getProduto().getId() == id2) {
				return true;
			}
		}
		return false;
	}
   
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataHora=" + data + ", total=" + total + ", itensP=" + itens + "]";
	}

	public void acrescentar(int id2, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId()== id2) {
				i.setQtd(i.getQtd()+qtd);
				return;
			}
		}
	}

	public void imprimirPedido() {
		for(Item i : itens) {
			System.out.println(i.getProduto().getId() +" | "+ i.getProduto().getNome() +" | " + i.getQtd());
		}
	}

	public boolean consultarItensPedido(int idP) {
		for(Item i : itens) {
			if(i.getProduto().getId() == idP) {
				return true;
			}
		}
		return false;
	}

	public void trocarQuantidade(int idP, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId() == idP) {
				i.setQtd(qtd);
				return;
			}
		}
	}

	public void confirmarPedido() {
		for(Item i : itens) {
			i.registarStockVal(i.getQtd());
		}
	}

	public void acrescentarMais(int idP, int qtd) {
		for(Item i : itens) {
			if(i.getProduto().getId()== idP) {
				i.setQtd(i.getQtd()+qtd);
				return;
			}
		}
	}

	public boolean verificarJaExiste(int idP) {
		for(Item i : itens) {
			if(i.getProduto().getId()==idP) {
				System.out.println("Encontrei");
				return true;
			}
		}
		System.out.println("nao Encontrei");
		return false;
	}
}
