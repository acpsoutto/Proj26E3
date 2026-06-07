package proj26E3;
/**
 * Class Utilizador - representa um utilizador do sistema do bar.
 */
public class Utilizador {
	
    private int numero;
    private String nome;
    private String email;
    private String pw;
    private TipoUtilizador tipo;
    
	/**
	 * CONSTRUTOR
	 * @param numero - numero de identificação do utilizador
	 * @param nome - nome do utilizador
	 * @param email - email do utilizador
	 * @param pw - palavra-passe do utilizador
	 * @param tipo - tipo de utilizador
	 */
	public Utilizador(int numero, String nome, String email, String pw, TipoUtilizador tipo) {
		this.numero = numero;
		this.nome = nome;
		this.email = email;
		this.pw = pw;
		this.tipo = tipo;
	}

	
	/**
	 * GET Devolve o email do utilizador
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * GET Devolve a palavra passe no utilizador
	 * @return pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * GET Devolve o numero de identificação do utilizador
	 * @return numero 
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * GET Devolve o nome do utilizador
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * GET Devolve o tipo de utilizador
	 * @return tipo
	 */
	public TipoUtilizador getTipo() {
		return tipo;
	}


	/**
	 *toString - Devolve as informações do utilizador
	 */
	@Override
	public String toString() {
		return "Funcionario: Número de Trabalhador:" + numero
				 + "| Nome: " + nome 
				 + "| Email: " + email
				 + "| Código de acesso: " + pw
				 + "| Tipo: " + tipo;
	}
}
