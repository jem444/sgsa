package ia.infra.negocio.sala;

import ia.infra.negocio.horario.HorarioSerie;
import java.util.ArrayList;

public class Sala {
	private int id;
	private String nome;
	private int numero;
	private String bloco;
	private String predio;
	private int capacidade;
	private ArrayList<HorarioSerie> listaHorario;
	
	public Sala(String nome, int capacidade) {
		this.nome   = nome;
		this.capacidade  = capacidade;
		//this.listaAula = new Vector<Aula>();
//		  for(int i = 0; i<=quantidade;i++){
//              Aula aul = new Aula();
//              aulas.add(aul);
//          }
		this.listaHorario= new ArrayList<HorarioSerie>();
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getPredio() {
		return predio;
	}

	public void setPredio(String predio) {
		this.predio = predio;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public ArrayList<HorarioSerie> getListaHorario() {
		return listaHorario;
	}

	public void setListaHorario(ArrayList<HorarioSerie> listaHorario) {
		this.listaHorario = listaHorario;
	}

}
