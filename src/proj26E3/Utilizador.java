package proj26E3;

public class Utilizador {
	
    private int numero;
    private String nome;
    private String email;
    private String pw;
    
    
	public Utilizador(int numero, String nome, String email, String pw) {
		this.numero = numero;
		this.nome = nome;
		this.email = email;
		this.pw = pw;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}


	@Override
	public String toString() {
		return "Funcionario: Número de Trabalhador:" + numero
				 + "| Nome:" + nome 
				 + "| Email:" + email
				 + "| Código de acesso:" + pw;
	}
	
}	
