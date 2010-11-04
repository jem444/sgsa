package unioeste.ia.trabalho1.pojo;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	
	/**Atributos**/
	private Integer cd_curso;
	private String nm_curso;
	private Periodo periodo;
	private List<Serie> listaSerie;

	/**contructor**/
	public Curso() {
		super();
		this.cd_curso = 0;
		this.nm_curso = "";
		this.periodo = Periodo.INTEGRAL;
		this.listaSerie = new ArrayList<Serie>();
		
	}

	public Curso(Integer cdCurso, String nmCurso, Periodo periodo, List<Serie> listaSerie) {
		super();
		cd_curso = cdCurso;
		nm_curso = nmCurso;
		this.periodo = periodo;
		this.listaSerie = listaSerie;
	}

	/**gets e sets**/	
	public void setNmCurso(String nmCurso) {
		this.nm_curso = nmCurso;
	}
	public String getNmCurso() {
		return nm_curso;
	}
	
	public List<Serie> getListaSerie() {
		return listaSerie;
	}

	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}
	
	public void setCd_curso(Integer cd_curso) {
		this.cd_curso = cd_curso;
	}
	
	public Integer getCd_curso() {
		return cd_curso;
	}
	
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Periodo getPeriodo() {
		return periodo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Curso)
			return ((Curso) obj).getNmCurso().equals(this.nm_curso);
		return false;
	}
	
	@Override
	public String toString() {
		return "=> Curso {" + this.cd_curso +"," + this.listaSerie +"," + this.nm_curso +"}";
	}



}