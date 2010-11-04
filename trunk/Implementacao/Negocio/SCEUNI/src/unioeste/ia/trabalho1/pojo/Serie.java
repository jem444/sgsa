package unioeste.ia.trabalho1.pojo;

import java.util.ArrayList;
import java.util.List;

public class Serie {

	/**Atributos**/
	private Integer cd_serie;
	private String nm_serie;
	private Periodo periodo;
	private Integer nu_serie;
	private Integer quantidade_matriculado_serie;
	private Curso curso;
	private List<Disciplina> listaDisciplina;
	
	/**Metodos**/
	
	/**contructor**/

	public Serie() {
		super();
		this.cd_serie = 0;
		this.nm_serie = "";
		this.periodo = Periodo.INTEGRAL;
		this.nu_serie = 0;
		this.quantidade_matriculado_serie = 0;
		this.curso = new Curso();
		this.listaDisciplina = new ArrayList<Disciplina>();
	}

	public Serie(Integer cdSerie, String nmSerie, Integer periodo,
			Integer nuSerie, Integer quantidadeMatriculadoSerie, Curso curso,
			List<Disciplina> listaDisciplina) {
		super();
		cd_serie = cdSerie;
		nm_serie = nmSerie;
		this.periodo = Periodo.INTEGRAL;
		nu_serie = nuSerie;
		quantidade_matriculado_serie = quantidadeMatriculadoSerie;
		this.curso = curso;
		this.listaDisciplina = listaDisciplina;
	}

	/**gets e sets**/
	
	public void setNmSerie(String nmSerie) {
		this.nm_serie = nmSerie;
	}
	public String getNmSerie() {
		return nm_serie;
	}

	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}

	public void setListaDisciplina(List<Disciplina> listadisciplina){
		this.listaDisciplina = listadisciplina;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setNu_serie(Integer nu_serie) {
		this.nu_serie = nu_serie;
	}
	public Integer getNu_serie() {
		return nu_serie;
	}
	public void setQuantidade_matriculado_serie(
			Integer quantidade_matriculado_serie) {
		this.quantidade_matriculado_serie = quantidade_matriculado_serie;
	}
	public Integer getQuantidade_matriculado_serie() {
		return quantidade_matriculado_serie;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCd_serie(Integer cd_serie) {
		this.cd_serie = cd_serie;
	}
	public Integer getCd_serie() {
		return cd_serie;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Serie)
			return ((Serie) obj).getNmSerie().equals(this.nm_serie) && ((Serie) obj).getCurso().equals(this.curso);
		return false;
	}
	@Override
	public String toString() {
		
		return "=> Serie {"+ nm_serie+","+ curso.getNmCurso() +"}";
	}
	
}
