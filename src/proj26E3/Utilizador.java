package proj26E3;

public abstract class Utilizador {
	
    private int numeroTrabalhador;
    private String nome;
    private String email;
    private String codigoAcesso;
    
    
	public Utilizador(int numeroTrabalhador, String nome, String email, String codigoAcesso) {
		this.numeroTrabalhador = numeroTrabalhador;
		this.nome = nome;
		this.email = email;
		this.codigoAcesso = codigoAcesso;
	}


	public String getNome() {
		return nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCodigoAcesso() {
		return codigoAcesso;
	}


	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}


	public int getNumeroTrabalhador() {
		return numeroTrabalhador;
	}
	
	@Override
	public String toString() {
		return "Funcionario: Número de Trabalhador:" + numeroTrabalhador
				 + "| Nome:" + nome 
				 + "| Email:" + email
				 + "| Código de acesso:" + codigoAcesso;
	}
	
}	
