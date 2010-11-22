package ia.infra.negocio.sala;

import java.util.Vector;

public class GrupoSala {
	private int id;
	private String nome;
	private String descricao;
	private Vector<Sala> salas; 
	
	public GrupoSala(String nome) {
		this.nome = nome;
		this.salas = new Vector<Sala>();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Vector<Sala> getSalas() {
		return salas;
	}
	
	public void addSala(Sala sala) {
		salas.add(sala);
	}
	
	public Sala getSala(int i) {
		return salas.get(i);
	}
	
	public Sala removeSala(int i) {
		return salas.remove(i);
	}

	public void setSalas(Vector<Sala> salas) {
		this.salas = salas;
	}

	
	
}
