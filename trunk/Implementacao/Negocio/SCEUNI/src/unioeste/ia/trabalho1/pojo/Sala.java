package unioeste.ia.trabalho1.pojo;

import java.util.ArrayList;
import java.util.List;

public class Sala {

	/**Atributos**/
	private Integer cdSala;
	private String nmSala;
	private Integer capacidade;
	private List<Aula> listaAula;
	private SalaStatus status;
	private List<Serie> listaSerie;
	private Curso curso;
	private Integer qtdAulasAlocadas;
	
	/**Metodos**/
	
	/**contructor**/
	public Sala() {
		super();
		this.cdSala = 0;
		this.nmSala = "";
		this.capacidade = 0;
		this.listaAula = new ArrayList<Aula>();
		this.status = SalaStatus.VAZIO;
		this.listaSerie = new ArrayList<Serie>();
		this.curso= new Curso();
		this.setQtdAulasAlocadas(0);
	}

	public Sala(Integer cdSala, String nmSala, Integer capacidade, SalaStatus status,
			List<Aula> listaAula, List<Serie> listaSerie, Curso curso, Integer qtdAulasAlocadas) {
		super();
		this.cdSala = cdSala;
		this.nmSala = nmSala;
		this.capacidade = capacidade;
		this.listaAula = listaAula;
		this.status = status;
		this.listaSerie = listaSerie;
		this.curso = curso;
		this.setQtdAulasAlocadas(qtdAulasAlocadas);
	}
	/**gets e sets**/
	

	public Integer getCdSala() {
		return cdSala;
	}

	public void setCdSala(Integer cdSala) {
		this.cdSala = cdSala;
	}

	public String getNmSala() {
		return nmSala;
	}

	public void setNmSala(String nmSala) {
		this.nmSala = nmSala;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public List<Aula> getListaAula() {
		return listaAula;
	}

	public void setListaAula(List<Aula> listaAula) {
		this.listaAula = listaAula;
	}

	public SalaStatus getStatus() {
		return status;
	}

	public void setStatus(SalaStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "\n=> Sala {" +cdSala+"," +capacidade+"," +listaAula+"}";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Sala)
			return ((Sala) obj).getNmSala().equals(this.nmSala);
		return false;
	}

	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}

	public List<Serie> getListaSerie() {
		return listaSerie;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setQtdAulasAlocadas(Integer qtdAulasAlocadas) {
		this.qtdAulasAlocadas = qtdAulasAlocadas;
	}

	public Integer getQtdAulasAlocadas() {
		return qtdAulasAlocadas;
	}
}
