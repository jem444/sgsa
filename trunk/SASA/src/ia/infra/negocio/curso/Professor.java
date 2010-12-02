package ia.infra.negocio.curso;

import java.io.Serializable;

public class Professor implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	
	public Professor(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
